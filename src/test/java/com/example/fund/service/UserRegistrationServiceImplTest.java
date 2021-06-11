package com.example.fund.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.example.fund.dto.UserDTO;
import com.example.fund.entity.User;
import com.example.fund.repository.AccountRepository;
import com.example.fund.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class UserRegistrationServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	private UserRegistrationServiceImpl userRegistrationServiceImpl;
	
	private static UserDTO user;
	
	private static UserDTO userPersisted;
	
	@BeforeAll
	public static void setUp() {
		LocalDate date = LocalDate.now();
		user = new UserDTO();
		user.setFirstName("Amar");
		user.setLastName("Doddi");
		user.setEmail("amarnath.doddi@hcl.com");
		user.setPhone("987654321");
		user.getLogin().setLoginId("amardoddi");
		user.getLogin().setPassword("test");
		user.setLastUpdated(date);
		
		userPersisted = new UserDTO();
		userPersisted.setId(1L);
		userPersisted.setFirstName("Amar");
		userPersisted.setLastName("Doddi");
		userPersisted.setEmail("amarnath.doddi@hcl.com");
		userPersisted.setPhone("987654321");
		userPersisted.getLogin().setLoginId("amardoddi");
		userPersisted.getLogin().setPassword("test");
		userPersisted.setLastUpdated(date);
	}
	
	@Test
	@DisplayName("Save User Test")
	@Order(1)
	void testCreateUer(){
		//context
		when(userRepository.save(any(User.class))).thenAnswer(i -> {
			User user = i.getArgument(0);
			user.setId(1L);
			return user;
		});
		//event
		UserDTO savedUser = userRegistrationServiceImpl.createUser(user);
		//outcome
		assertEquals(savedUser,userPersisted);
	}
	
	@Test
	@DisplayName("Update User Test")
	@Order(2)
	void testUpdateUser() {
		when(userRepository.saveAndFlush(any(User.class))).thenAnswer(i -> {
			User user = i.getArgument(0);
			user.setId(1L);
			user.setLoginId("temploginid");
			return user;
		});
		UserDTO updateUser = userRegistrationServiceImpl.updateUser(user);
		assertEquals("temploginid",updateUser.getLogin().getLoginId());
	}
	
	@Test
	@Order(3)
	@DisplayName("Delete User Test")
	void testDeleteUser() {
		//when(userRepository.findById(1L)).thenReturn(Optional.of(user.getUser()));

		assertTrue(userRegistrationServiceImpl.deleteUser(1L));

	    //verify(userRepository, times(1)).delete(user.getUser());
	}
	@Test
	@Order(4)
	@DisplayName("Test findByLoginId")
	void testfindByLoginId() {
		when(userRepository.findByLoginId(any(String.class))).thenAnswer(i -> {
			String loginId = i.getArgument(0);
			user.getLogin().setLoginId(loginId);
			return user.getUser();
		});
		
		UserDTO dbUser = userRegistrationServiceImpl.findByLoginId("amardoddi");
		assertNotNull(dbUser);
		assertEquals("amardoddi",userRegistrationServiceImpl.findByLoginId("amardoddi").getLogin().getLoginId());
	}
	
	@Test
	@DisplayName("Test findByFirstnameEndingWith")
	void testfindByFirstnameEndingWith() {
		List<UserDTO> userAccount =  userRegistrationServiceImpl.findByFirstnameEndingWith("mar");
		assertNotNull(userAccount);
	}
	@Test
	@DisplayName("Test findByFirstnameStartingWith")
	void testfindByFirstnameStartingWith() {
		List<UserDTO> userAccount =  userRegistrationServiceImpl.findByFirstnameStartingWith("ama");
		assertNotNull(userAccount);
	}
	@Test
	@DisplayName("Test findByFirstnameLike")
	void testfindByFirstnameLike() {
		List<UserDTO> userAccount =  userRegistrationServiceImpl.findByFirstnameLike("ma");
		assertNotNull(userAccount);
	}
	@Test
	@DisplayName("Test findDistinctByLastNameAndFirstName")
	void testfindDistinctByLastNameAndFirstName() {
		List<UserDTO> userAccount =  userRegistrationServiceImpl.findDistinctByLastNameAndFirstName("amarnath","doddi");
		assertNotNull(userAccount);
	}
	@Test
	@DisplayName("Test findByEmail")
	void testfindByEmail() {
		UserDTO userAccount =  userRegistrationServiceImpl.findByEmail("amarnath.doddi@hcl.com");
		assertNotNull(userAccount);
	}
	@Test
	@DisplayName("Test getUsersSortBy")
	void testgetUsersSortBy() {
		List<UserDTO> userAccount =  userRegistrationServiceImpl.getUsersSortBy(Sort.by("firstName").ascending());
		assertNotNull(userAccount);
	}
	@Test
	@DisplayName("Test getUsers")
	void testgetUsers() {
		List<UserDTO> userAccount =  userRegistrationServiceImpl.getUsers();
		assertNotNull(userAccount);
	}
	@Test
	@DisplayName("Test getUser")
	void testgetUser() {
		UserDTO userAccount =  userRegistrationServiceImpl.getUser(1000L);
		assertNotNull(userAccount);
	}

}
