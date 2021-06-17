package com.example.fund.controller;

import java.util.List;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fund.dto.BalanceTransfer;
import com.example.fund.dto.BeneficiaryDTO;
import com.example.fund.dto.FundTransfer;
import com.example.fund.dto.UserAccountDTO;
import com.example.fund.exception.InsufficientFundException;
import com.example.fund.service.BeneficiaryService;
import com.example.fund.service.FundTransferService;

@RestController
//@RequestMapping("/api/fund")
public class FundTransferController {
	Logger logger = LoggerFactory.getLogger(FundTransferController.class);
	@Autowired
	private FundTransferService fundTransferService;
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@PutMapping("/")
	public ResponseEntity<Boolean> transfer(@RequestBody FundTransfer fundTransfer){
		
		BiFunction<Double,Double,Double> addAmount = (a,b) -> a + b;
		BiFunction<Double,Double,Double> subAmount = (a,b) -> a -b;
		UserAccountDTO userAccount = fundTransferService.getByUserId(fundTransfer.getUserId());
		//insufficient funds
		if(fundTransfer.getAmount()>userAccount.getAccountDTO().getBalance()) {
			String message = String.format("Insufficient fund :%s to transfer. Available balance only :%s",fundTransfer.getAmount(),userAccount.getAccountDTO().getBalance());
			logger.error(message);
			throw new InsufficientFundException(message);
		}
		userAccount.getAccountDTO().setBalance(subAmount.apply(userAccount.getAccountDTO().getBalance(), fundTransfer.getAmount()));
		fundTransferService.updateAccount(userAccount);
		BeneficiaryDTO beneficiary = beneficiaryService.getBeneficiary(fundTransfer.getBeneficaryId());
		beneficiary.getAccount().setBalance(addAmount.apply(beneficiary.getAccount().getBalance(), fundTransfer.getAmount()));
		beneficiaryService.updateBeneficiary(beneficiary);
		logger.info("Amount transfered successfully.");
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@PutMapping("/account/")
	public ResponseEntity<Boolean> transferToAccount(@RequestBody BalanceTransfer fundTransfer){
		
		BiFunction<Double,Double,Double> addAmount = (a,b) -> a + b;
		BiFunction<Double,Double,Double> subAmount = (a,b) -> a -b;
		UserAccountDTO userAccount = fundTransferService.getByAccountNumber(fundTransfer.getUserAccountNumber());
		UserAccountDTO beneficierAccount = fundTransferService.getByAccountNumber(fundTransfer.getBeneficaryAccountNumber());
		
		//insufficient funds
		if(fundTransfer.getAmount()>userAccount.getAccountDTO().getBalance()) {
			String message = String.format("Insufficient fund :%s to transfer. Available balance only :%s",fundTransfer.getAmount(),userAccount.getAccountDTO().getBalance());
			logger.error(message);
			throw new InsufficientFundException(message);
		}
		userAccount.getAccountDTO().setBalance(subAmount.apply(userAccount.getAccountDTO().getBalance(), fundTransfer.getAmount()));
		fundTransferService.updateAccount(userAccount);
		beneficierAccount.getAccountDTO().setBalance(addAmount.apply(beneficierAccount.getAccountDTO().getBalance(), fundTransfer.getAmount()));
		fundTransferService.updateAccount(beneficierAccount);
		logger.info("Amount transfered successfully.");
		return new ResponseEntity<>(true,HttpStatus.OK);
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
	@PostMapping("/account/")
	public ResponseEntity<UserAccountDTO> createAccount(@RequestBody UserAccountDTO account){
		UserAccountDTO createdAccount = fundTransferService.createAccount(account);
		if(createdAccount==null) {
			logger.error("Error creating the the Account.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(createdAccount,HttpStatus.OK);
	} 
	
	@GetMapping("/account/")
	public ResponseEntity<List<UserAccountDTO>> getAccounts(){
		List<UserAccountDTO> accounts = fundTransferService.getAccounts();
		if(accounts==null) {
			logger.info("No accounts exist in the system.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(accounts,HttpStatus.OK);
	}
}
