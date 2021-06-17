package com.example.fund.dto;

public class FundTransfer {
	private long userId;
	private long beneficaryId;
	private double amount;
	public long getBeneficaryId() {
		return beneficaryId;
	}
	public void setBeneficaryId(long beneficaryId) {
		this.beneficaryId = beneficaryId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getUserId() {
		return userId;
	}
	
}
