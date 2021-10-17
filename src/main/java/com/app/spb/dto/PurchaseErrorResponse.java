package com.app.spb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseErrorResponse {

	private String errorMessage;
	private int errorCode;
}
