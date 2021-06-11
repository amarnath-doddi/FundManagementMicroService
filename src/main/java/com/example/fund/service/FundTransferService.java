package com.example.fund.service;

import com.example.fund.dto.UserAccountDTO;

public interface FundTransferService {
	public UserAccountDTO getByUserId(Long userId);
	public UserAccountDTO updateAccount(UserAccountDTO account);
	public UserAccountDTO getByAccountId(Long accountId);
}
