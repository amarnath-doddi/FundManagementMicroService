package com.example.fund.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fund.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByIdGreaterThan(Long id);
	List<User> findByIdGreaterThanOrderByFirstNameDesc(Long id);
	List<User> findByFirstName(String firstName);
	List<User> findByLastName(String lastName);
	User findByEmail(String email);
	User findByLoginId(String userId);
	List<User> findDistinctByLastNameAndFirstName(String lastName, String firstName);
	List<User> findByFirstNameLike(String firstName);
	List<User> findByFirstNameStartingWith(String firstName);
	List<User> findByFirstNameEndingWith(String firstName);
}
