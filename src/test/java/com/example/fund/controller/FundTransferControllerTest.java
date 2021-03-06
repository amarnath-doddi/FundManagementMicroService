package com.example.fund.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fund.dto.AccountDTO;
import com.example.fund.dto.BeneficiaryDTO;
import com.example.fund.dto.FundTransfer;
import com.example.fund.dto.UserAccountDTO;
import com.example.fund.exception.InsufficientFundException;
import com.example.fund.service.BeneficiaryServiceImpl;
import com.example.fund.service.FundTransferServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class FundTransferControllerTest {
	@Mock
	private FundTransferServiceImpl fundTransferServiceImpl;
	@Mock
	private BeneficiaryServiceImpl beneficiaryService;
	@InjectMocks
	private FundTransferController fundTransferController;
	private static FundTransfer fundTransfer;
	private static BeneficiaryDTO beneficiary;
	private static UserAccountDTO account;
	@BeforeAll
	public static void setUp() {
		fundTransfer = new FundTransfer();
		fundTransfer.setAmount(500.00);
		fundTransfer.setBeneficaryId(3001L);
		fundTransfer.setUserId(1000L);
		
		beneficiary = new BeneficiaryDTO();
		beneficiary.setId(3001l);
		beneficiary.setAccount(new AccountDTO(2001L,32435366L,0.00));
		beneficiary.setIfscCode("HDFC2324");
		beneficiary.setName("TestBeneficiary");
		
		account = new UserAccountDTO();
		account.setAccountDTO(new AccountDTO(2001L,676766689L,23432.00));
		account.setUserId(1000L);
		
	}
	
	@Test
	@DisplayName("Test Fund Transfer")
	@Order(1)
	void testTransfer() {
		when(beneficiaryService.getBeneficiary(fundTransfer.getBeneficaryId())).thenReturn(beneficiary);
		when(fundTransferServiceImpl.getByUserId(account.getUserId())).thenReturn(account);
		//when(fundTransferServiceImpl.transfer(any(FundTransfer.class))).thenReturn(true);

		assertTrue(fundTransferController.transfer(fundTransfer).getBody());
	}
	@Test
	@DisplayName("Negitive senario:Test Insufficent Funds to Transfer")
	@Order(2)
	void testInsufficeFundsToTransfer() {
		account.getAccountDTO().setBalance(500.00);
		fundTransfer.setAmount(1000.00);
		fundTransfer.setUserId(account.getUserId());
		//when(beneficiaryService.getBeneficiary(fundTransfer.getBeneficaryId())).thenReturn(beneficiary);
		when(fundTransferServiceImpl.getByUserId(account.getUserId())).thenReturn(account);
		//when(userRegistrationServiceImpl.getUser(any(Long.class))).thenReturn(user);
		//when(fundTransferServiceImpl.transfer(any(FundTransfer.class))).thenThrow(InsufficientFundException.class);

		assertThrows(InsufficientFundException.class, ()->fundTransferController.transfer(fundTransfer));
	}
	
	@Test
	void testGetAccountByUserId() {
		when(fundTransferServiceImpl.getByUserId(any(Long.class))).thenAnswer(i -> {
			Long userId = i.getArgument(0);
			account.setUserId(userId);
			return account;
		});
		
		UserAccountDTO dbAccount = fundTransferController.getAccountByUserId(1000L).getBody();
		assertEquals(1000L, dbAccount.getUserId());
	}
	@Test
	void testGetAccountById() {
		when(fundTransferServiceImpl.getByAccountId(any(Long.class))).thenAnswer(i -> {
			Long accountId = i.getArgument(0);
			account.setUserId(accountId);
			return account;
		});
		
		UserAccountDTO dbAccount = fundTransferController.getAccountById(2001L).getBody();
		assertEquals(2001L, dbAccount.getUserId());
	}
}
