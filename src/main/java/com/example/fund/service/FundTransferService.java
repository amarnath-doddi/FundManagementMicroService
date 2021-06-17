package com.example.fund.service;

import java.util.List;

import com.example.fund.dto.UserAccountDTO;

public interface FundTransferService {
	public UserAccountDTO getByUserId(Long userId);
	public UserAccountDTO updateAccount(UserAccountDTO account);
	public UserAccountDTO getByAccountId(Long accountId);
	public UserAccountDTO createAccount(UserAccountDTO account);
	public List<UserAccountDTO> getAccounts();
	public UserAccountDTO getByAccountNumber(Long accountNumber);
}
