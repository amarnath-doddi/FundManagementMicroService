package com.example.fund.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="beneficiary")
public class Beneficiary {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	@Column(name = "account_number")
	private Long accountNumber;
	@Column(name = "name")
	private String name;
	@Column(name = "ifsc_code")
	private String ifscCode;
	@Column(name = "balance")
	private Double balance;
	//@ManyToOne(targetEntity = Account.class,fetch =FetchType.LAZY)
	@Column(name = "account_id", nullable = false)
	private Long accountId;
	public Beneficiary() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getAccountId() {
		return accountId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null||getClass()!=obj.getClass()) {
			return false;
		}
		
		 Beneficiary beneficiary = (Beneficiary) obj;
	        return id == beneficiary.id 
	        		&& balance == beneficiary.balance
	        		&& accountId == beneficiary.accountId
	        		&& accountNumber == beneficiary.accountNumber
	        		&& Objects.equals(ifscCode, beneficiary.ifscCode)
	        		&& Objects.equals(name, beneficiary.name);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, balance, accountId, accountNumber, ifscCode, name);
	    }
}
