package com.example.fund.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

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

import com.example.fund.entity.User;
import com.example.fund.repository.LoginRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class LoginServiceImplTest {
	@Mock
	private LoginRepository loginRepository;
	@InjectMocks
	private LoginServiceImpl loginServiceImpl;
	
	private static User user;
	
	@BeforeAll
	public static void setUp() {
		LocalDate date = LocalDate.now();
		user = new User();
		user.setFirstName("Amar");
		user.setLastName("Doddi");
		user.setEmail("amarnath.doddi@hcl.com");
		user.setPhone("987654321");
		user.setLoginId("amardoddi");
		user.setPassword("test");
		user.setLastUpdated(date);
	}
	
	@Test
	@DisplayName("Athenticate user Login")
	@Order(1)
	void loginTest() {
		when(loginRepository.findByLoginIdAndPassword("amardoddi", "test")).thenReturn(user);
		
		boolean isSuccessful = loginServiceImpl.login("amardoddi", "test");
		
		assertTrue(isSuccessful);
		
	}
	
	@Test
	@DisplayName("Reset Login Id")
	@Order(2)
	void resetLoginIdTest() {
		user.setLoginId("test");
		when(loginRepository.findByLoginId(any(String.class))).thenReturn(user);
		boolean isSuccessful = loginServiceImpl.resetUserId("test", "test");
		
		assertTrue(isSuccessful);
	}
	
	@Test
	@DisplayName("Reset Password")
	@Order(3)
	void resetPasswordTest() {
		user.setPassword("password");
		user.setLoginId("amardoddi");
		when(loginRepository.saveAndFlush(any(User.class))).thenReturn(user);
		when(loginRepository.findByLoginId(any(String.class))).thenReturn(user);
		boolean isSuccessful = loginServiceImpl.resetPassword("amardoddi", "password");
		
		assertTrue(isSuccessful);
	}
	
	@Test
	void testIsLoginSuccessfull() {
		assertTrue(loginServiceImpl.isLoginSuccessfull(user));
	}
	
	@Test
	void testIsLoginUnSuccessfull() {
		assertFalse(loginServiceImpl.isLoginSuccessfull(null));
	}
}
