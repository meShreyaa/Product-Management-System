package com.example.ProductProject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ProductProject.dto.ProductDto;
import com.example.ProductProject.entity.Product;
import com.example.ProductProject.utility.ResponseStructure;

public interface ProductService {

	
	public ResponseEntity<ResponseStructure<Product>> save(ProductDto  productDTO);
	public ResponseEntity<ResponseStructure<Product>> update(int productId, Product product);
	public ResponseEntity<ResponseStructure<Product>> delete(int productId);
	public ResponseEntity<ResponseStructure<Product>> findByProductId(int productId);
	public ResponseEntity<ResponseStructure<List<Product>>> findAll();
	
	
	
}
