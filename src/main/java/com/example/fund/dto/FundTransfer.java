package com.example.fund.dto;

public class FundTransfer {
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
	
}
