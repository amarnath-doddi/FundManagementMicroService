package com.example.fund.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fund.dto.AccountDTO;
import com.example.fund.dto.BeneficiaryDTO;
import com.example.fund.entity.Beneficiary;
import com.example.fund.repository.BeneficiaryRepository;

@ExtendWith(MockitoExtension.class)
class BeneficiaryServiceImplTest {
	@Mock
	private BeneficiaryRepository beneficiaryRepository;
	@InjectMocks
	private BeneficiaryServiceImpl beneficiaryServiceImpl;
	
	static BeneficiaryDTO beneficiary;
	
	static BeneficiaryDTO perisistedBeneficiary;
	
	@BeforeAll
	public static void setUp() {
		beneficiary = new BeneficiaryDTO();
		beneficiary.setAccount(new AccountDTO(2001L,32435366L,0.00));
		beneficiary.setIfscCode("HDFC2324");
		beneficiary.setName("TestBeneficiary");
		
		perisistedBeneficiary = new BeneficiaryDTO();
		perisistedBeneficiary.setId(3001L);
		perisistedBeneficiary.setAccount(new AccountDTO(2001L,32435366L,0.00));
		perisistedBeneficiary.setIfscCode("HDFC2324");
		perisistedBeneficiary.setName("TestBeneficiary");
	}
	
	@Test
	@DisplayName("Test Create Beneficiary")
	void testCreateBeneficiary() {
		when(beneficiaryRepository.save(any(Beneficiary.class))).thenAnswer(i -> {
			Beneficiary beneficiary = i.getArgument(0);
			beneficiary.setId(3011L);
			return beneficiary;
		});
		
		BeneficiaryDTO savedBeneficiary = beneficiaryServiceImpl.createBeneficiary(beneficiary);
		
		assertEquals(3011L, savedBeneficiary.getId());
	}
	
	@Test
	@DisplayName("Update Beneficiary Test")
	void testUpdateBeneficiary() {
		when(beneficiaryRepository.saveAndFlush(any(Beneficiary.class))).thenAnswer(i -> {
			Beneficiary beneficiary = i.getArgument(0);
			beneficiary.setId(3001L);
			beneficiary.setBalance(100.00);
			return beneficiary;
		});
		BeneficiaryDTO updatedBeneficiary = beneficiaryServiceImpl.updateBeneficiary(beneficiary);
		assertEquals(100.00, updatedBeneficiary.getAccount().getBalance());
	}
	@Test
	@DisplayName("Delete Beneficiary Test")
	void testDeleteUser() {
		//when(userRepository.findById(1L)).thenReturn(Optional.of(user.getUser()));

		Boolean isDeleted = beneficiaryServiceImpl.deleteBeneficiary(3001L);
		assertTrue(isDeleted);
	    verify(beneficiaryRepository, times(1)).deleteById(3001L);
	}
	@Test
	@DisplayName("Test getBeneficiries")
	void testgetBeneficiries() {
		List<BeneficiaryDTO> beneficiearyDTO =  beneficiaryServiceImpl.getBeneficiries();
		assertNotNull(beneficiearyDTO);
	}
	@Test
	@DisplayName("Test getById")
	void testgetById() {
		BeneficiaryDTO beneficiearyDTO =  beneficiaryServiceImpl.getById(3001L);
		assertNotNull(beneficiearyDTO);
	}
	@Test
	@DisplayName("Test getBeneficiary")
	void testgetBeneficiary() {
		BeneficiaryDTO beneficiearyDTO =  beneficiaryServiceImpl.getBeneficiary(3001L);
		assertNotNull(beneficiearyDTO);
	}
	@Test
	@DisplayName("Test getByBeneficiaryAccountNumber")
	void testgetByBeneficiaryAccountNumber() {
		BeneficiaryDTO beneficiearyDTO =  beneficiaryServiceImpl.getByBeneficiaryAccountNumber(8989898999L);
		assertNotNull(beneficiearyDTO);
	}
	@Test
	@DisplayName("Test getByAccountId")
	void testGetByAccountId() {
		List<BeneficiaryDTO> beneficiearyDTO =  beneficiaryServiceImpl.getByAccountId(2001L);
		assertNotNull(beneficiearyDTO);
	}
}
