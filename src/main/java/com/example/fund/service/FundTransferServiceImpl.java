package com.example.fund.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fund.dto.AccountDTO;
import com.example.fund.dto.BeneficiaryDTO;
import com.example.fund.dto.UserAccountDTO;
import com.example.fund.entity.Account;
import com.example.fund.entity.Beneficiary;
import com.example.fund.repository.AccountRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public UserAccountDTO getByUserId(Long userId) {
		return getAccountDTO(accountRepository.findByUserId(userId));
	}
	@Override
	public UserAccountDTO getByAccountNumber(Long accountNumber) {
		return getAccountDTO(accountRepository.findByAccountNumber(accountNumber));
	}
	@Override
	public UserAccountDTO updateAccount(UserAccountDTO account) {
		return getAccountDTO(accountRepository.saveAndFlush(getAccount(account)));
	}
	@Override
	public UserAccountDTO getByAccountId(Long accountId) {
		Optional<Account> account = accountRepository.findById(accountId);
		
		return getAccountDTO(account.orElse(new Account()));
	}
	@Override
	public UserAccountDTO createAccount(UserAccountDTO account) {
		return getAccountDTO(accountRepository.saveAndFlush(getAccount(account)));
	}

	@Override
	public List<UserAccountDTO> getAccounts() {
		return accountRepository.findAll().stream().map(account -> getAccountDTO(account)).collect(Collectors.toList());
	}

	private UserAccountDTO getAccountDTO(Account account) {
		//account = Optional.ofNullable(account).orElse(new Account());
		List<Beneficiary> beneficiaries = Optional.ofNullable(account.getBeneficiaries()).orElse(new ArrayList<>());
		List<BeneficiaryDTO> beneficiaryDto = beneficiaries.stream().map(b -> getBeneficiaryDTO(b)).collect(Collectors.toList());
		return new UserAccountDTO(account.getUserId(), beneficiaryDto, new AccountDTO(account.getId(),account.getAccountNumber(),account.getBalance()));
	}
	public BeneficiaryDTO getBeneficiaryDTO(Beneficiary beneficiary) {
		BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO();
		beneficiaryDTO.setAccount(new AccountDTO(beneficiary.getAccountId(),beneficiary.getAccountNumber(),beneficiary.getBalance()));
		beneficiaryDTO.setIfscCode(beneficiary.getIfscCode());
		beneficiaryDTO.setName(beneficiary.getName());
		beneficiaryDTO.setId(beneficiary.getId());
		return beneficiaryDTO;
	}
	public Beneficiary getBeneficiary(BeneficiaryDTO beneficiaryDto) {
		AccountDTO accountDto = Optional.ofNullable(beneficiaryDto.getAccount()).orElse(new AccountDTO());
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAccountId(accountDto.getId());
		beneficiary.setAccountNumber(accountDto.getAccountNumber());
		beneficiary.setBalance(accountDto.getBalance());
		beneficiary.setIfscCode(beneficiaryDto.getIfscCode());
		beneficiary.setName(beneficiaryDto.getName());
		beneficiary.setId(beneficiaryDto.getId());
		return beneficiary;
		
	}
	public Account getAccount(UserAccountDTO accountDto) {
		Account account = new Account();
		account.setId(accountDto.getAccountDTO().getId());
		account.setAccountNumber(accountDto.getAccountDTO().getAccountNumber());
		account.setBalance(accountDto.getAccountDTO().getBalance());
		account.setUserId(accountDto.getUserId());
		List<BeneficiaryDTO> beneficiariesDto = Optional.ofNullable(accountDto.getBeneficiaries()).orElse(new ArrayList<>());
		account.setBeneficiaries(beneficiariesDto.stream().map(b -> getBeneficiary(b)).collect(Collectors.toList()));
		return account;
	}

}
