package com.example.fund.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> exceptionDetails = new ArrayList<>();
		exceptionDetails.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(400L,exceptionDetails);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<String> handleUserNotFoundException(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<>("Not Valid as there is Validation Error! "+ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotfoundException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(UserNotfoundException ex, WebRequest request) {
		List<String> exceptionDetails = new ArrayList<>();
		exceptionDetails.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(404L,exceptionDetails);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InsufficientFundException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(InsufficientFundException ex, WebRequest request) {
		List<String> exceptionDetails = new ArrayList<>();
		exceptionDetails.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(404L,exceptionDetails);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> exceptionDetails = new ArrayList<>();
		for(ObjectError er : ex.getBindingResult().getAllErrors()) {
			exceptionDetails.add(er.getDefaultMessage());	
		}
		
		ErrorResponse error = new ErrorResponse(410L,exceptionDetails);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
