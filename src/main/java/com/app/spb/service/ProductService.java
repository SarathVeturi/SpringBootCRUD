package com.app.spb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.spb.entity.Product;
import com.app.spb.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
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
			existing.setTotal(existing.getPrice()*existing.getQuantity());
		}
		return productRepository.save(existing);
	}
}
