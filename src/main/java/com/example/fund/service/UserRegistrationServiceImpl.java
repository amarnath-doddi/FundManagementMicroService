package com.example.fund.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fund.dto.UserDTO;
import com.example.fund.entity.Account;
import com.example.fund.entity.User;
import com.example.fund.repository.AccountRepository;
import com.example.fund.repository.UserRepository;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional
	public UserDTO createUser(UserDTO user) {
		Optional<User> savedUserOpt = Optional.ofNullable(userRepository.save(user.getUser()));
		LocalDate localDate = LocalDate.now();
		UserDTO savedUser = new UserDTO(savedUserOpt);
		Account account = new Account();
		account.setBalance(0.00);
		account.setUserId(savedUser.getId());
		account.setAccountNumber(localDate.toEpochDay());
		accountRepository.saveAndFlush(account);
		return savedUser;
	}

	@Override
	public Boolean deleteUser(Long id) {
		userRepository.deleteById(id);
		return true;
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		UserDTO savedUser = null;
		savedUser = user.getUserDTO(userRepository.saveAndFlush(user.getUser()));
		return savedUser;
	}

	@Override
	public UserDTO getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		return new UserDTO(user);
	}

	@Override
	public List<UserDTO> getUsers() {
		return userRepository.findAll().stream().map(user -> new UserDTO(Optional.ofNullable(user))).collect(Collectors.toList());
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public List<UserDTO> getUsersSortBy(Sort by) {
		return userRepository.findAll(by).stream().map(user -> new UserDTO(Optional.ofNullable(user))).collect(Collectors.toList());
	}

	@Override
	public UserDTO findByEmail(String email) {
		return new UserDTO(Optional.ofNullable(userRepository.findByEmail(email)));
	}

	@Override
	public List<UserDTO> findDistinctByLastNameAndFirstName(String lastName, String firstName) {
		return userRepository.findDistinctByLastNameAndFirstName(lastName, firstName).stream().map(user -> new UserDTO(Optional.ofNullable(user))).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> findByFirstnameLike(String firstName) {
		return userRepository.findByFirstNameLike(firstName).stream().map(user -> new UserDTO(Optional.ofNullable(user))).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> findByFirstnameStartingWith(String firstName) {
		return userRepository.findByFirstNameStartingWith(firstName).stream().map(user -> new UserDTO(Optional.ofNullable(user))).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> findByFirstnameEndingWith(String firstName) {
		return userRepository.findByFirstNameEndingWith(firstName).stream().map(user -> new UserDTO(Optional.ofNullable(user))).collect(Collectors.toList());
	}

	@Override
	public UserDTO findByLoginId(String loginId) {
		return new UserDTO(Optional.ofNullable(userRepository.findByLoginId(loginId)));
	}

}
