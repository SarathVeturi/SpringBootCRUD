package com.app.spb.utils;

import java.util.HashMap;
import java.util.Map;

import com.app.spb.exception.InsufficientAmountException;

public class PaymentUtils {
	
	private static Map<String, Double> paymentMap = new HashMap<>();
	
	static {
		paymentMap.put("acc1", 10000.0);
		paymentMap.put("acc2", 8000.0);
		paymentMap.put("acc3", 12000.0);
		paymentMap.put("acc4", 5000.0);
	}
	
	public static boolean validatePayAmount(String accNo, double toBepaidAmount) throws InsufficientAmountException{
		if(toBepaidAmount > paymentMap.get(accNo)) {
			throw new InsufficientAmountException("Insufficient Amount in account");
		}else {
			return true;
		}
	}

}
