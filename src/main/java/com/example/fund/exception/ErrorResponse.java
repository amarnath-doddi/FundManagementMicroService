package com.example.fund.exception;

import java.util.List;

public class ErrorResponse {
	private Long errorCode;
	private List<String> errorDetails;
	public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ErrorResponse(Long errorCode, List<String> errorDetails) {
		super();
		this.errorCode = errorCode;
		this.errorDetails = errorDetails;
	}

	public Long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}
	public List<String> getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(List<String> errorDetails) {
		this.errorDetails = errorDetails;
	}
	
}
