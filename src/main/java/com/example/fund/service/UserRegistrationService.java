package com.example.fund.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.fund.dto.UserDTO;
import com.example.fund.entity.User;

public interface UserRegistrationService {
	public UserDTO createUser(UserDTO user);
	public Boolean deleteUser(Long id);
	public UserDTO updateUser(UserDTO user);
	public UserDTO getUser(Long id);
	public List<UserDTO> getUsers();
	public Page<User> findAll(Pageable pageable);
	public List<UserDTO> getUsersSortBy(Sort by);
	public UserDTO findByEmail(String email);
	public UserDTO findByLoginId(String userId);
	public List<UserDTO> findDistinctByLastNameAndFirstName(String lastName, String firstName);
	public List<UserDTO> findByFirstnameLike(String firstName);
	public List<UserDTO> findByFirstnameStartingWith(String firstName);
	public List<UserDTO> findByFirstnameEndingWith(String firstName);
}
