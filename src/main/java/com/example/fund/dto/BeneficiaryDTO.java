package com.example.fund.dto;

import java.util.Objects;
import java.util.Optional;

import com.example.fund.entity.Beneficiary;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BeneficiaryDTO {
	private Long id;
	private String name;
	private String ifscCode;
	private AccountDTO account;
	public BeneficiaryDTO() {
	}
	public BeneficiaryDTO(Optional<Beneficiary> beneficiaryOptional) {
		if(beneficiaryOptional.isPresent()) {
			account = new AccountDTO();
			Beneficiary beneficiary = beneficiaryOptional.get();
			this.id = beneficiary.getId();
			this.account.setId(beneficiary.getAccountId());
			this.account.setAccountNumber(beneficiary.getAccountNumber());
			this.account.setBalance(beneficiary.getBalance());
			this.ifscCode = beneficiary.getIfscCode();
			this.name = beneficiary.getName();
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	public AccountDTO getAccount() {
		return account;
	}
	public BeneficiaryDTO getBeneficiaryDTO(Beneficiary beneficiary) {
		account = new AccountDTO();
		this.id = beneficiary.getId();
		this.account.setId( beneficiary.getAccountId());
		this.account.setAccountNumber(beneficiary.getAccountNumber());
		this.account.setBalance(beneficiary.getBalance());
		this.ifscCode = beneficiary.getIfscCode();
		this.name = beneficiary.getName();
		return this;
	}
	
	@JsonIgnore
	public Beneficiary getBeneficiary() {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setId(id);
		beneficiary.setAccountId(account.getId());
		beneficiary.setAccountNumber(account.getAccountNumber());
		beneficiary.setBalance(account.getBalance());
		beneficiary.setIfscCode(ifscCode);
		beneficiary.setName(name);
		return beneficiary;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null||getClass()!=obj.getClass()) { return false;}
		
		 BeneficiaryDTO beneficiary = (BeneficiaryDTO) obj;
	        return id == beneficiary.id 
	        		&& account.getBalance() == beneficiary.getAccount().getBalance()
	        		&& account.getId() == beneficiary.getAccount().getId()
	        		&& account.getAccountNumber() == beneficiary.getAccount().getAccountNumber()
	        		&& Objects.equals(ifscCode, beneficiary.ifscCode)
	        		&& Objects.equals(name, beneficiary.name);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, account.getBalance(), account.getId(), account.getAccountNumber(), ifscCode, name);
	    }
}
