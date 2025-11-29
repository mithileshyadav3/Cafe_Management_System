package com.auth.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.model.Product;
import com.auth.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
public Product addProduct(Product product) {
	return  productRepo.save(product);
}
public List<Product> allProduct(){
	return productRepo.findAll();
}
}
