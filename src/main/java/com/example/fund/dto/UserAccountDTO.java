package com.example.fund.dto;

import java.util.List;
import java.util.Objects;

public class UserAccountDTO {
	private Long userId;
	private List<BeneficiaryDTO> beneficiaries;
	private AccountDTO account;
	public UserAccountDTO() {
	}
	
	public UserAccountDTO(Long userId, List<BeneficiaryDTO> beneficiaries, AccountDTO account) {
		super();
		this.userId = userId;
		this.beneficiaries = beneficiaries;
		this.account = account;
	}

	public void setBeneficiaries(List<BeneficiaryDTO> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<BeneficiaryDTO> getBeneficiaries() {
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
