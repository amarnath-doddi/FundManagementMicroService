package com.example.fund.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fund.entity.User;
@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
	public User findByLoginIdAndPassword(String userId, String password);
	public User findByLoginId(String userId);
}
