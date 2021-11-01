package com.app.spb.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.spb.entity.PaymentInfo;
import com.app.spb.entity.Product;
import com.app.spb.repository.PaymentInfoRepository;
import com.app.spb.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class SpbDaoImpl implements SpbDao {
	
	private final ProductRepository productRepository;
	private final PaymentInfoRepository paymentInfoRepository;
	
	@Override
	public Product saveProduct(Product product) {
		product.setTotal(product.getPrice() * product.getQuantity());
		return productRepository.save(product);
	}
	
	@Override
	public List<Product> saveAllProducts(List<Product> products) {
		products.forEach(item -> item.setTotal(item.getPrice() * item.getQuantity()));
		return productRepository.saveAll(products);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Product> getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public String deleteProduct(int id) {
		productRepository.deleteById(id);
		return String.format("removed product id:%d", id);
	}
	
	@Override
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
	
	@Override
	public PaymentInfo savePaymentInfo(PaymentInfo paymentInfo) {
		return paymentInfoRepository.save(paymentInfo);	
	}

}

