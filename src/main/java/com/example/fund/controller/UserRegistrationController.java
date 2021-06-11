package com.example.fund.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fund.dto.UserDTO;
import com.example.fund.entity.User;
import com.example.fund.exception.DuplicateEntryException;
import com.example.fund.exception.UserNotfoundException;
import com.example.fund.service.UserRegistrationService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRegistrationController {
	Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<UserDTO> users = userRegistrationService.getUsers();
		if(users==null) {
			logger.info("There are no register users exist.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		UserDTO user = userRegistrationService.getUser(id);
		if(user==null) {
			String message = String.format("User doesn't exist with id :%s", id);
			logger.error(message);
			throw new UserNotfoundException(message);
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
		UserDTO existingUser = userRegistrationService.findByEmail(user.getEmail());
		if(existingUser!=null && !user.getEmail().equals(existingUser.getEmail())) {
			logger.error("User already exist!");
			throw new DuplicateEntryException("User already exist!");
		}
		UserDTO createdUser = userRegistrationService.createUser(user);
		if(createdUser==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
	} 
	
	@PutMapping("/")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user){
		UserDTO udatedUser = userRegistrationService.updateUser(user);
		if(udatedUser==null || !udatedUser.equals(user)) {
			logger.error("Error while updating the user!");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(udatedUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
		boolean isDeleted = userRegistrationService.deleteUser(id);
		if(!isDeleted) {
			logger.error("User delete unsuccessfull!");
			return new ResponseEntity<>(isDeleted,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(isDeleted,HttpStatus.OK);
	} 
	
	@GetMapping("/page/size")
	public ResponseEntity<Map<String,Object>> getUsersByPagination(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "2") int size){
		try {
			List<User> users ;
			Pageable paging = PageRequest.of(page, size);
			Page<User> userPage = userRegistrationService.findAll(paging);
			users = userPage.getContent();
			Map<String,Object> resp = new HashMap<>();
			resp.put("users",users);
			resp.put("currentPage", userPage.getNumber());
			resp.put("totalItems", userPage.getTotalElements());
			resp.put("totalPages", userPage.getTotalPages());
			return new ResponseEntity<>(resp,HttpStatus.OK);
		
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/sort/{sortby}/order/{orderby}")
	public List<UserDTO> getUsersBySort(@PathVariable String sortby,@PathVariable String orderby){
		Sort sort = (orderby.equalsIgnoreCase("asc"))?Sort.by(sortby).ascending():Sort.by(sortby).descending();
		return userRegistrationService.getUsersSortBy(sort);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
		UserDTO user = userRegistrationService.findByEmail(email);
		if(user==null) {
			String message = String.format("User doesn't exist with email :%s", email);
			logger.error(message);
			throw new UserNotfoundException(message);
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	@GetMapping("/firstName/{firstName}/lastName/{lastName}")
	public List<UserDTO> getDistinctUsersByLastNameAndFirstName(@PathVariable String lastName,@PathVariable String firstName){
		return userRegistrationService.findDistinctByLastNameAndFirstName(lastName, firstName);
	}
}
