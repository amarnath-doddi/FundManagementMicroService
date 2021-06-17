package com.example.fund.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fund.dto.BeneficiaryDTO;
import com.example.fund.exception.BeneficiaryNotfoundException;
import com.example.fund.exception.DuplicateEntryException;
import com.example.fund.service.BeneficiaryService;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {
	Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@GetMapping("/")
	public ResponseEntity<List<BeneficiaryDTO>> getBeneficiaries(){
		List<BeneficiaryDTO> beneficies = beneficiaryService.getBeneficiries();
		if(beneficies==null) {
			logger.info("No beneficiaries exist in the system.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(beneficies,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<BeneficiaryDTO> getBeneficiary(@PathVariable Long id){
		BeneficiaryDTO beneficiary = beneficiaryService.getBeneficiary(id);
		if(beneficiary==null) {
			String message = String.format("Beneficiary doesn't exist with id :%s",id); 
			logger.error(message);
			throw new BeneficiaryNotfoundException(message);
		}
		return new ResponseEntity<>(beneficiary,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<BeneficiaryDTO> createBeneficiary(@RequestBody BeneficiaryDTO beneficiary){
		BeneficiaryDTO existingBeneficiary= beneficiaryService.getByBeneficiaryAccountNumber(beneficiary.getAccount().getAccountNumber());
		if(existingBeneficiary!=null) {
			logger.error("Beneficiary already exist!");
			throw new DuplicateEntryException("Beneficiary already exist!");
		}
		BeneficiaryDTO createdBeneficiary = beneficiaryService.createBeneficiary(beneficiary);
		if(createdBeneficiary==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(createdBeneficiary,HttpStatus.OK);
	} 
	
	@PutMapping("/")
	public ResponseEntity<BeneficiaryDTO> updateBeneficiary(@RequestBody BeneficiaryDTO beneficiary){
		BeneficiaryDTO udatedBeneficiary = beneficiaryService.updateBeneficiary(beneficiary);
		if(udatedBeneficiary==null || !udatedBeneficiary.equals(beneficiary)) {
			logger.error("Beneficiary updating failed!");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(udatedBeneficiary,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
		Boolean isDeleted = beneficiaryService.deleteBeneficiary(id);
		return new ResponseEntity<>(isDeleted,HttpStatus.OK);
	}
	
	@GetMapping("/account/{id}")
	public ResponseEntity<List<BeneficiaryDTO>> getBeneficiariesByAccountId(@PathVariable Long id){
		List<BeneficiaryDTO> beneficiaries = beneficiaryService.getByAccountId(id);
		if(beneficiaries==null) {
			String message = String.format("Beneficiary doesn't exist for account id :%s",id);
			throw new BeneficiaryNotfoundException(message);
		}
		return new ResponseEntity<>(beneficiaries,HttpStatus.OK);
	}
	
}
