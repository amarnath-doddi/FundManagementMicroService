package com.example.fund.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fund.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	public Account findByUserId(Long userId);
}
