package com.app.spb.dao;

import java.util.List;

import com.app.spb.entity.PaymentInfo;
import com.app.spb.entity.Product;

public interface SpbDao {

	public Product saveProduct(Product product);

	public List<Product> saveAllProducts(List<Product> products);

	public List<Product> getAllProducts();

	public Product getProductById(int id);

	public List<Product> getProductByName(String name);

	public String deleteProduct(int id);

	public Product updateProduct(Product product);

	public PaymentInfo savePaymentInfo(PaymentInfo paymentInfo);

}
