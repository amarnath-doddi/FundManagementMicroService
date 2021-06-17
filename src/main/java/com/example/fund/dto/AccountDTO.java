package com.example.fund.dto;

import java.util.Objects;

public class AccountDTO {
	private Long id;
	private Long accountNumber;
	private Double balance;
	
	public AccountDTO() {
	}
	
	public AccountDTO(Long id, Long accountNumber, Double balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
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
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO account = (AccountDTO) o;
        return id == account.id  && accountNumber == account.accountNumber
        		&& balance == account.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance);
    }
}
