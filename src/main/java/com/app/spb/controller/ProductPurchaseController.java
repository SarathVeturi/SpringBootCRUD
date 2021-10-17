package com.app.spb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.spb.dto.PurchaseAckResponse;
import com.app.spb.dto.PurchaseRequest;
import com.app.spb.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductPurchaseController {
	
	private final ProductService productService;
	
	@PostMapping("/buyProduct")
	public PurchaseAckResponse getResponse(@RequestBody PurchaseRequest request) {
		return productService.getPurchaseDetails(request);
	}
}
