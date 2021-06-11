package com.example.fund.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fund.dto.BeneficiaryDTO;
import com.example.fund.repository.BeneficiaryRepository;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	@Override
	public BeneficiaryDTO updateBeneficiary(BeneficiaryDTO beneficiary) {
		return new BeneficiaryDTO(Optional.ofNullable(beneficiaryRepository.saveAndFlush(beneficiary.getBeneficiary())));
	}
	@Override
	public BeneficiaryDTO getById(Long id) {
		return new BeneficiaryDTO(beneficiaryRepository.findById(id));
	}
	@Override
	public List<BeneficiaryDTO> getBeneficiries() {
		return beneficiaryRepository.findAll().stream().map(bf -> new BeneficiaryDTO(Optional.ofNullable(bf))).collect(Collectors.toList());
	}
	@Override
	public BeneficiaryDTO createBeneficiary(BeneficiaryDTO beneficiary) {
		return new BeneficiaryDTO(Optional.ofNullable(beneficiaryRepository.save(beneficiary.getBeneficiary())));
	}
	@Override
	public Boolean deleteBeneficiary(Long id) {
		 beneficiaryRepository.deleteById(id);
		 return true;
	}
	@Override
	public BeneficiaryDTO getBeneficiary(Long id) {
		return new BeneficiaryDTO(Optional.ofNullable(beneficiaryRepository.findById(id).get()));
	}
	@Override
	public BeneficiaryDTO getByBeneficiaryAccountNumber(Long beneficiaryAccountNumber) {
		return new BeneficiaryDTO(Optional.ofNullable(beneficiaryRepository.findByAccountNumber(beneficiaryAccountNumber)));
	}
	@Override
	public List<BeneficiaryDTO> getByAccountId(Long accountId) {
		return beneficiaryRepository.findByAccountId(accountId).stream().map(bf -> new BeneficiaryDTO(Optional.ofNullable(bf))).collect(Collectors.toList());
	}

}
