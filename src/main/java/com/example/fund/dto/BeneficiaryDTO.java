package com.example.fund.dto;

import java.util.Objects;

public class BeneficiaryDTO {
	private Long id;
	private String name;
	private String ifscCode;
	private AccountDTO account;
	public BeneficiaryDTO() {
	}
	public BeneficiaryDTO(Long id, String name, String ifscCode, AccountDTO account) {
		super();
		this.id = id;
		this.name = name;
		this.ifscCode = ifscCode;
		this.account = account;
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
