package com.example.fund.dto;

public class BalanceTransfer {
	private long userAccountNumber;
	private long beneficaryAccountNumber;
	private double amount;
	public long getUserAccountNumber() {
		return userAccountNumber;
	}
	public void setUserAccountNumber(long userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}
	public long getBeneficaryAccountNumber() {
		return beneficaryAccountNumber;
	}
	public void setBeneficaryAccountNumber(long beneficaryAccountNumber) {
		this.beneficaryAccountNumber = beneficaryAccountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
