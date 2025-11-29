package com.auth.controller;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth.model.Product;
import com.auth.service.ProductService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
@PostMapping("/addprod")
public Product addProduct(@RequestParam("images")MultipartFile file,
		                  @RequestParam("name") String name,
		                  @RequestParam("price") Integer price,
		                  @RequestParam("category")String category,
		                  @RequestParam("status")String status) throws IOException {
	String uploadDir="uploads/";
	String fileName=file.getOriginalFilename();
	Path path =Paths.get(uploadDir+fileName);
	Files.createDirectories(path.getParent());
	Files.write(path, file.getBytes());
	Product product = new Product();
    product.setName(name);
    product.setCategory(category);
    product.setPrice(price);
    product.setStatus(status);
    product.setImages(fileName);
	return productService.addProduct(product);
}
@GetMapping("/allprod")
public List<Product>allProduct(){
	return productService.allProduct();
}
}
