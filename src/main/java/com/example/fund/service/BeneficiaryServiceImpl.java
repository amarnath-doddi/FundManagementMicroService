package com.example.fund.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fund.dto.AccountDTO;
import com.example.fund.dto.BeneficiaryDTO;
import com.example.fund.entity.Beneficiary;
import com.example.fund.repository.BeneficiaryRepository;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	@Override
	public BeneficiaryDTO updateBeneficiary(BeneficiaryDTO beneficiary) {
		return getBeneficiaryDTO(beneficiaryRepository.saveAndFlush(getBeneficiary(beneficiary)));
	}
	@Override
	public List<BeneficiaryDTO> getBeneficiries() {
		return beneficiaryRepository.findAll().stream().map(bf -> getBeneficiaryDTO(bf)).collect(Collectors.toList());
	}
	@Override
	public BeneficiaryDTO createBeneficiary(BeneficiaryDTO beneficiary) {
		return getBeneficiaryDTO(beneficiaryRepository.save(getBeneficiary(beneficiary)));
	}
	@Override
	public Boolean deleteBeneficiary(Long id) {
		 beneficiaryRepository.deleteById(id);
		 return true;
	}
	@Override
	public BeneficiaryDTO getBeneficiary(Long id) {
		return getBeneficiaryDTO(beneficiaryRepository.findById(id).orElse(new Beneficiary()));
	}
	@Override
	public BeneficiaryDTO getByBeneficiaryAccountNumber(Long beneficiaryAccountNumber) {
		return getBeneficiaryDTO(beneficiaryRepository.findByAccountNumber(beneficiaryAccountNumber));
	}
	@Override
	public List<BeneficiaryDTO> getByAccountId(Long accountId) {
		return beneficiaryRepository.findByAccountId(accountId).stream().map(bf -> getBeneficiaryDTO(bf)).collect(Collectors.toList());
	}
	private BeneficiaryDTO getBeneficiaryDTO(Beneficiary beneficiary) {
		beneficiary = Optional.ofNullable(beneficiary).orElse(new Beneficiary());
		return new BeneficiaryDTO(beneficiary.getId(), beneficiary.getName(), beneficiary.getIfscCode(), new AccountDTO(beneficiary.getAccountId(), beneficiary.getAccountNumber(),beneficiary.getBalance()));
	}
	
	public Beneficiary getBeneficiary(BeneficiaryDTO beneficiaryDto) {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setId(beneficiaryDto.getId());
		beneficiary.setName(beneficiaryDto.getName());
		beneficiary.setIfscCode(beneficiaryDto.getIfscCode());
		beneficiary.setAccountId(beneficiaryDto.getAccount().getId());
		beneficiary.setAccountNumber(beneficiaryDto.getAccount().getAccountNumber());
		beneficiary.setBalance(beneficiaryDto.getAccount().getBalance());
		return beneficiary;
	}


}
