package com.app.spb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.spb.dto.PurchaseErrorResponse;
import com.app.spb.exception.InsufficientAmountException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(InsufficientAmountException.class)
	public ResponseEntity<PurchaseErrorResponse> prepareException(InsufficientAmountException ex){
		PurchaseErrorResponse errorResponse = new PurchaseErrorResponse();
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(ex.getMessage());
		return new ResponseEntity<PurchaseErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
