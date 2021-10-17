package com.app.spb.controller;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.spb.entity.Product;
import com.app.spb.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	private final ObjectMapper mapper;

	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		try {
			System.out.println(mapper.writeValueAsString(product));
		} catch (Exception ex) {
			System.out.println(ex);}
		return productService.saveProduct(product);
	}

	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> products) {
		return productService.saveAllProducts(products);
	}

	@GetMapping("/fetchAll")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/fetchById/{id}")
	public Product getById(@PathVariable int id) {
		return productService.getProductById(id);
	}

	@GetMapping("/fetchByName/{name}")
	public List<Product> getByName(@PathVariable @NonNull String name) {
		return productService.getProductByName(name);
	}

	@PutMapping("/updateProduct")
	public Product modifyProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteId(@PathVariable int id) {
		return productService.deleteProduct(id);
	}
}
