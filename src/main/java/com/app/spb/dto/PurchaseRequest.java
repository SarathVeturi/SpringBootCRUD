package com.app.spb.dto;

import com.app.spb.entity.PaymentInfo;
import com.app.spb.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {

	private Product product;
	private PaymentInfo paymentInfo;

}
