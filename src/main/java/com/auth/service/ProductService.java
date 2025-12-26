package com.auth.service;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public String deleteProduct(int id) {
	if(productRepo.existsById(id)) {
	 productRepo.deleteById(id);
	 return "Product delete successfully";
	}
	return "Product not found";
}

public Product updaProduct(int id, String name, String category,String status ,Double price, MultipartFile image) {
	// TODO Auto-generated method stub
	Product existing=productRepo.findById(id).orElse(null);
	if(existing==null) {
		return null;
	}
	existing.setName(name);
	existing.setCategory(category);
    existing.setStatus(status);
    existing.setPrice(price);
    if (image != null && !image.isEmpty()) {
        String fileName = image.getOriginalFilename();

        try {
            Path uploadPath = Paths.get("uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(image.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            existing.setImages(fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	return productRepo.save(existing);

}
public List<Product> searchProduct(String keyword) {
	// TODO Auto-generated method stub
	return productRepo.searchProduct(keyword);
}}
