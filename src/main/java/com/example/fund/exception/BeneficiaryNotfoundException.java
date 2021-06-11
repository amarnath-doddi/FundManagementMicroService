package com.example.fund.exception;

public class BeneficiaryNotfoundException extends RuntimeException {
	   private static final long serialVersionUID = 1L;
	   public BeneficiaryNotfoundException(String message) {
			super(message);
	   }
	   
}