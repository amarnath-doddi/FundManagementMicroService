package com.example.fund.dto;

import java.util.List;
import java.util.Objects;

import com.example.fund.entity.Account;
import com.example.fund.entity.Beneficiary;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserAccountDTO {
	private Long userId;
	private List<Beneficiary> beneficiaries;
	private AccountDTO account;
	public UserAccountDTO() {
	}
	public UserAccountDTO(Account account) {
		this.account = new AccountDTO();
		this.userId = account.getUserId();
		this.beneficiaries = account.getBeneficiaries();
		this.account.setAccountNumber(account.getAccountNumber());
		this.account.setBalance(account.getBalance());
		this.account.setId(account.getId());
		
	}
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	public Long getUserId() {
		return userId;
	}
	public void setAccountDTO(AccountDTO account) {
		this.account = account;
	}
	public AccountDTO getAccountDTO() {
		return account;
	}
	@JsonIgnore
	public Account getUserAccount() {
		return new Account(this.account.getId(),this.userId, this.account.getAccountNumber(), this.account.getBalance(),this.beneficiaries);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountDTO userAccount = (UserAccountDTO) o;
        return userId == userAccount.userId  && beneficiaries == userAccount.beneficiaries
        		&& account.equals(userAccount.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, beneficiaries, account);
    }
}
