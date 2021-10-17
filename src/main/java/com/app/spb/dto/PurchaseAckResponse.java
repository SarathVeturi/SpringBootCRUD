package com.app.spb.dto;

import com.app.spb.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseAckResponse {

	private String status;
	private double paidAmount;
	private String acknowledgementId;
	private Product product;
}
