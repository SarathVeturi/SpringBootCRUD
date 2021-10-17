package com.app.spb.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.spb.constants.SpbConstants;
import com.app.spb.dao.SpbDao;
import com.app.spb.dto.PurchaseAckResponse;
import com.app.spb.dto.PurchaseRequest;
import com.app.spb.entity.PaymentInfo;
import com.app.spb.entity.Product;
import com.app.spb.utils.PaymentUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final SpbDao spbDao;
	private final ObjectMapper objectMapper;

	private final Logger log = LoggerFactory.getLogger(ProductService.class);
	
	@Transactional // (readOnly = false, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PurchaseAckResponse getPurchaseDetails(PurchaseRequest request) {

		
		Product product = request.getProduct();
		product = spbDao.saveProduct(product);

		PaymentInfo paymentInfo = request.getPaymentInfo();

		PaymentUtils.validatePayAmount(paymentInfo.getAccountNo(), product.getTotal());

		paymentInfo.setProductId(product.getId());
		paymentInfo.setAmount(product.getTotal());
		
		spbDao.savePaymentInfo(paymentInfo);
		try {
			log.info("Product Info : {}", objectMapper.writeValueAsString(product));
			log.info("Payment Info : {}", objectMapper.writeValueAsString(paymentInfo));
		} catch (JsonProcessingException ex) {
			log.error("Exception occured - {}", ex.getMessage());
		}
		
		return new PurchaseAckResponse(SpbConstants.SUCCESS, paymentInfo.getAmount(),
				UUID.randomUUID().toString().split("-")[0], product);
	}

}
