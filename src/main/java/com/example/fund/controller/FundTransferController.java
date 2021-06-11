package com.example.fund.controller;

import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fund.dto.BeneficiaryDTO;
import com.example.fund.dto.FundTransfer;
import com.example.fund.dto.UserAccountDTO;
import com.example.fund.dto.UserDTO;
import com.example.fund.exception.InsufficientFundException;
import com.example.fund.service.BeneficiaryService;
import com.example.fund.service.FundTransferService;
import com.example.fund.service.UserRegistrationService;

@RestController
@RequestMapping("/api/fund")
public class FundTransferController {
	Logger logger = LoggerFactory.getLogger(FundTransferController.class);
	@Autowired
	private FundTransferService fundTransferService;
	@Autowired
	private BeneficiaryService beneficiaryService;
	@Autowired
	private UserRegistrationService userService;
	
	@PutMapping("/")
	public ResponseEntity<UserDTO> transfer(@RequestBody FundTransfer fundTransfer){
		
		BiFunction<Double,Double,Double> addAmount = (a,b) -> a + b;
		BiFunction<Double,Double,Double> subAmount = (a,b) -> a -b;
		UserAccountDTO account = fundTransferService.getByAccountId(beneficiaryService.getBeneficiary(fundTransfer.getBeneficaryId()).getAccount().getId());
		//insufficient funds
		if(fundTransfer.getAmount()>account.getAccountDTO().getBalance()) {
			String message = String.format("Insufficient fund :%s to transfer. Available balance only :%s",fundTransfer.getAmount(),account.getAccountDTO().getBalance());
			logger.error(message);
			throw new InsufficientFundException(message);
		}
		account.getAccountDTO().setBalance(subAmount.apply(account.getAccountDTO().getBalance(), fundTransfer.getAmount()));
		fundTransferService.updateAccount(account);
		BeneficiaryDTO beneficiary = beneficiaryService.getBeneficiary(fundTransfer.getBeneficaryId());
		beneficiary.getAccount().setBalance(addAmount.apply(beneficiary.getAccount().getBalance(), fundTransfer.getAmount()));
		beneficiaryService.updateBeneficiary(beneficiary);
		UserDTO user = userService.getUser(account.getUserId());
		logger.info("Amount transfered successfully.");
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/account/user/{userId}")
	public ResponseEntity<UserAccountDTO> getAccountByUserId(@PathVariable Long userId){
		UserAccountDTO account = fundTransferService.getByUserId(userId);
		return new ResponseEntity<>(account,HttpStatus.OK);
	}
	
	@GetMapping("/account/{accountId}")
	public ResponseEntity<UserAccountDTO> getAccountById(@PathVariable Long accountId){
		UserAccountDTO account = fundTransferService.getByAccountId(accountId);
		return new ResponseEntity<>(account,HttpStatus.OK);
	}
}
