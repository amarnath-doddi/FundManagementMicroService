package com.example.fund.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fund.dto.UserAccountDTO;
import com.example.fund.entity.Account;
import com.example.fund.repository.AccountRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public UserAccountDTO getByUserId(Long userId) {
		return new UserAccountDTO(accountRepository.findByUserId(userId));
	}
	@Override
	public UserAccountDTO updateAccount(UserAccountDTO account) {
		return new UserAccountDTO(accountRepository.saveAndFlush(account.getUserAccount()));
	}
	@Override
	public UserAccountDTO getByAccountId(Long accountId) {
		Optional<Account> account = accountRepository.findById(accountId);
		
		return new UserAccountDTO(account.orElse(new Account()));
	}

}
