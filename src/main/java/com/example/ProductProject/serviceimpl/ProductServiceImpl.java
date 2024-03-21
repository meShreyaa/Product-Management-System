package com.example.ProductProject.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ProductProject.dto.ProductDto;
import com.example.ProductProject.entity.Product;
import com.example.ProductProject.execption.ProductNotFoundByIdException;
import com.example.ProductProject.repository.ProductRepository;
import com.example.ProductProject.service.ProductService;
import com.example.ProductProject.utility.ResponseStructure;

@Service
public class ProductServiceImpl implements ProductService {

	// @Autowired
	private ProductRepository productRepository;
	private ResponseStructure<Product> responseStructure;
	private ResponseStructure<List<Product>> listStructure;

	public ProductServiceImpl(ProductRepository productRepository, ResponseStructure<Product> responseStructure,
			ResponseStructure<List<Product>> listStructure) {
		super();
		this.productRepository = productRepository;
		this.responseStructure = responseStructure;
		this.listStructure = listStructure;
	}

//	@Override
//	public ResponseEntity<ResponseStructure<Product>> save(Product product) {
//		Product product2 = productRepository.save(product);
//		
//		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value()).setMessage("Data Saved Successfully").setData(product2));
//
////		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
////		responseStructure.setStatuscode(200);
////		responseStructure.setMessage("Data Inerted Successfully");
////		responseStructure.setData(product2);
////		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
//		
//	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> update(int productId, Product updatedProduct) {
//		Optional<Product> optional = productRepository.findById(productId);
//
//		if (optional.isPresent()) {
//			Product existingProduct = optional.get();
//			// updatedProduct.setProductId(existingProduct.getProductId());
//			Product product = productRepository.save(updatedProduct);
//
//			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
//			responseStructure.setStatuscode(200);
//			responseStructure.setMessage("Data updated Successfully");
//			responseStructure.setData(product);
//
//			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
//		} else {
//			return null;
//		}
		return productRepository.findById(productId).map(existingProduct -> {
			updatedProduct.setProductId(existingProduct.getProductId());
			existingProduct = productRepository.save(updatedProduct);
			return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("Product Updated Successfully").setData(existingProduct));
		}).orElseThrow(() -> new ProductNotFoundByIdException("No Product Found To Update"));
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> delete(int productId) {
//		Optional<Product> optional = productRepository.findById(productId);
//
//		if (optional.isPresent()) {
//			Product existingProduct = optional.get();
//
//			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
//			responseStructure.setStatuscode(HttpStatus.OK.value());
//			responseStructure.setMessage("Data Deleted Successfully");
//			responseStructure.setData(existingProduct);
//
//			productRepository.delete(existingProduct);
//
//			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
//		} else {
//			return null;
//		}

//		return productRepository.;

		Optional<Product> optional = productRepository.findById(productId);
		return optional.map(product -> {
			productRepository.delete(product);

			return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("Product Deleted").setData(product));
		}).orElseThrow(() -> new ProductNotFoundByIdException("Product Not Found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> findByProductId(int productId) {
//		Optional<Product> optional=productRepository.findById(productId);
//		
//		if(optional.isPresent()) {
//			Product foundProduct=optional.get();
//			
//			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
//			responseStructure.setStatuscode(HttpStatus.FOUND.value());
//			responseStructure.setMessage("Data Fetched Successfully");
//			responseStructure.setData(foundProduct);
//			
//			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.FOUND);
//		}
//		else {
//		return null;
//		}

		// ! Another Way
//		return productRepository.findById(productId).map(p->{
//			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
//			return ResponseEntity.ok(responseStructure);
//		}).orElseThrow(()-> new ProductNotFoundByIdException("No Product Found!!"));

		// !RECOMMENDED WAY

		return productRepository.findById(productId)
				.map(product -> ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
						.setMessage("Product Found").setData(product)))
				.orElseThrow(() -> new ProductNotFoundByIdException("Product Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {

		List<Product> products = productRepository.findAll();
		// return products;
		if (!products.isEmpty()) {
			return ResponseEntity.ok(listStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("List Of Products Is Fetched").setData(products));
		} else
			throw new ProductNotFoundByIdException("No Product exists in the list");
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> save(ProductDto productDTO) {
		// mapping DTO to entity
		Product product = productRepository.save(mapToProduct(productDTO, new Product()));
		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
				.setMessage("Product Saved SuccessFully").setData(product));
	}

	private Product mapToProduct(ProductDto productDTO, Product product) {
		product.setProductName(productDTO.getProductName());
		product.setProductPrice(productDTO.getProductPrice());
		return product;
		
		//return Product.builder().productName(productDTO.getProductName()).build();
	}

}
