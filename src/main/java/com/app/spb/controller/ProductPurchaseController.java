package com.app.spb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.spb.dto.PurchaseAckResponse;
import com.app.spb.dto.PurchaseRequest;
import com.app.spb.exception.InsufficientAmountException;
import com.app.spb.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductPurchaseController {

	private final ProductService productService;
	private final ObjectMapper objectMapper;
	
	private final Logger log = LoggerFactory.getLogger(ProductService.class);

	@PostMapping("/buyProduct")
	public ResponseEntity<PurchaseAckResponse> getResponse(@RequestBody PurchaseRequest request) throws InsufficientAmountException {

		try {
			log.info("Request to API - {}", objectMapper.writeValueAsString(request));
		} catch (JsonProcessingException ex) {
			log.error("Error occured - {}", ex.getMessage());
		}

		PurchaseAckResponse response = productService.getPurchaseDetails(request);
		try {
			log.info("Response from API - {}", objectMapper.writeValueAsString(response));
		} catch (JsonProcessingException ex) {
			log.error("Error occured - {}", ex.getMessage());
		}
		return new ResponseEntity<PurchaseAckResponse>(response, HttpStatus.OK);
	}
}
