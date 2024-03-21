package com.example.ProductProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductProject.dto.ProductDto;
import com.example.ProductProject.entity.Product;
import com.example.ProductProject.serviceimpl.ProductServiceImpl;
import com.example.ProductProject.utility.ErrorStructure;
import com.example.ProductProject.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ProductController {

	
	private ProductServiceImpl productService;

	

	public ProductController(ProductServiceImpl productService) {
		super();
		this.productService = productService;
	}

	@PostMapping("/products")
	@Operation(description = "The endpoint is used to add a new product in the Database", responses = {
			@ApiResponse(responseCode = "200", description = "Product saved Successsfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Inputs", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	public ResponseEntity<ResponseStructure<Product>> save(@RequestBody ProductDto productDto){
		return productService.save(productDto);
	}
	
	@PutMapping("/products/{productId}")
	@Operation(description = "The endpoint is used to update the existing product based on ID", responses = {
			@ApiResponse(responseCode = "200", description = "Product Updated Successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid inputs" , content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			}),
			@ApiResponse(responseCode = "404", description ="No product found using the specified ID", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	
	public ResponseEntity<ResponseStructure<Product>> update(@PathVariable int productId, @RequestBody Product product){
		return productService.update(productId, product);
	}

	@DeleteMapping("/products/{productId}")
	@Operation(description = "The endpoint is used to delete a product in the Database", responses = {
			@ApiResponse(responseCode = "200", description = "Product deleted Successsfully"),
			@ApiResponse(responseCode = "404", description = "Cannot find the product with specified ID", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	public ResponseEntity<ResponseStructure<Product>> delete(@PathVariable int productId, @RequestBody Product product){
		return productService.delete(productId);
		
	}
	@GetMapping("/products/{productId}")
	@Operation(description = "The endpoint is used to fetch the product based on the ID", responses = {
			@ApiResponse(responseCode =  "200", description = "Product Found"),
			@ApiResponse(responseCode = "404", description = "Product not found by the given product ID", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	public ResponseEntity<ResponseStructure<Product>> findByProdctid(@PathVariable int productId, @RequestBody Product product){
		return productService.findByProductId(productId);
	}
	
	@GetMapping("/products")
	@Operation(description = "The endpoint is used to fetch all the products", responses = {
			@ApiResponse(responseCode =  "200", description = "Product Found"),
			@ApiResponse(responseCode = "404", description = "No Product found in the List", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	public ResponseEntity<ResponseStructure<List<Product>>> findAll(){
		return productService.findAll();
	}
	
 	}
	
	

