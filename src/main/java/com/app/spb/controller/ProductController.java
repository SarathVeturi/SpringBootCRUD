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

import com.app.spb.dao.SpbDao;
import com.app.spb.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	
	private final SpbDao spbDao;
	private final ObjectMapper mapper;

	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		try {
			System.out.println(mapper.writeValueAsString(product));
		} catch (Exception ex) {
			System.out.println(ex);}
		return spbDao.saveProduct(product);
	}

	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> products) {
		return spbDao.saveAllProducts(products);
	}

	@GetMapping("/fetchAll")
	public List<Product> getAllProducts() {
		return spbDao.getAllProducts();
	}

	@GetMapping("/fetchById/{id}")
	public Product getById(@PathVariable int id) {
		return spbDao.getProductById(id);
	}

	@GetMapping("/fetchByName/{name}")
	public List<Product> getByName(@PathVariable @NonNull String name) {
		return spbDao.getProductByName(name);
	}

	@PutMapping("/updateProduct")
	public Product modifyProduct(@RequestBody Product product) {
		return spbDao.updateProduct(product);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteId(@PathVariable int id) {
		return spbDao.deleteProduct(id);
	}
}
