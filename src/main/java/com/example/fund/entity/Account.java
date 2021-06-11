package com.example.fund.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	//@ManyToOne(targetEntity = User.class,fetch =FetchType.LAZY)
	@Column(name = "user_id", nullable = false)
	private Long userId;
	@Column(name = "account_number")
	private Long accountNumber;
	@Column(name = "balance")
	private Double balance;
	@OneToMany(targetEntity = Beneficiary.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private List<Beneficiary> beneficiaries;
	public Account() {
	}
	
	public Account(Long id, Long userId, Long accountNumber, Double balance, List<Beneficiary> beneficiaries) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.beneficiaries = beneficiaries;
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserId() {
		return userId;
	}
}
