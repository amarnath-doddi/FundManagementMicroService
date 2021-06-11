package com.example.fund.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fund.entity.Beneficiary;
@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
	public List<Beneficiary> findByAccountId(Long accountId);
	public Beneficiary findByAccountNumber(Long accountNumber);
}
