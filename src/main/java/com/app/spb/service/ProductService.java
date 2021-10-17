package com.app.spb.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.spb.dto.PurchaseAckResponse;
import com.app.spb.dto.PurchaseRequest;
import com.app.spb.entity.PaymentInfo;
import com.app.spb.entity.Product;
import com.app.spb.repository.PaymentInfoRepository;
import com.app.spb.repository.ProductRepository;
import com.app.spb.utils.PaymentUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final PaymentInfoRepository paymentInfoRepository;

	@Transactional// (readOnly = false, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PurchaseAckResponse getPurchaseDetails(PurchaseRequest request) {

		Product product = request.getProduct();
		product = saveProduct(product);

		PaymentInfo paymentInfo = request.getPaymentInfo();
		PaymentUtils.validatePayAmount(paymentInfo.getAccountNo(), product.getTotal());

		paymentInfo.setProductId(product.getId());
		paymentInfo.setAmount(product.getTotal());

		paymentInfoRepository.save(paymentInfo);
		return new PurchaseAckResponse("SUCCESS", paymentInfo.getAmount(), UUID.randomUUID().toString().split("-")[0],
				product);
	}

	public Product saveProduct(Product product) {
		product.setTotal(product.getPrice() * product.getQuantity());
		return productRepository.save(product);
	}

	public List<Product> saveAllProducts(List<Product> products) {
		products.forEach(item -> item.setTotal(item.getPrice() * item.getQuantity()));
		return productRepository.saveAll(products);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(int id) {
		return productRepository.findById(id).orElse(null);
	}

	public List<Product> getProductByName(String name) {
		return productRepository.findByName(name);
	}

	public String deleteProduct(int id) {
		productRepository.deleteById(id);
		return String.format("removed product id:%d", id);
	}

	public Product updateProduct(Product product) {
		Product existing = getProductById(product.getId());
		if (existing != null) {
			existing.setName(product.getName());
			existing.setPrice(product.getPrice());
			existing.setQuantity(product.getQuantity());
			existing.setTotal(existing.getPrice() * existing.getQuantity());
		}
		return productRepository.save(existing);
	}

}
