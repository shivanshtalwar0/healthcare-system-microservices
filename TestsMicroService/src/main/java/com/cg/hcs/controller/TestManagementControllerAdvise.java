package com.cg.hcs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.hcs.exception.TestManagementException;

@RestControllerAdvice
public class TestManagementControllerAdvise {
	
	
	@ExceptionHandler(TestManagementException.class)
	public ResponseEntity<String> handleTestManagementException(TestManagementException excep){
		return new ResponseEntity<String>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleOtherExceptions(Exception excep){
		return new ResponseEntity<String>(excep.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
