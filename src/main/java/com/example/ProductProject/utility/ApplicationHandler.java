package com.example.ProductProject.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.ProductProject.entity.Product;
import com.example.ProductProject.execption.ProductNotFoundByIdException;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {

	private ErrorStructure errorStructure;

	public ApplicationHandler(ErrorStructure errorStructure) {
		super();
		this.errorStructure = errorStructure;
	}

//	public ResponseEntity<ErrorStructure<Product>> productNotFoundById(ProductNotFoundByIdException ex){
//		ErrorStructure<Product> errorStructure=new ErrorStructure<Product>();
//		errorStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
//		errorStructure.setErrorMessage(ex.getMessage());
//		errorStructure.setRootCause("No Product Found with specified Id");
//		
//		return new ResponseEntity<ErrorStructure<Product>>(errorStructure,HttpStatus.NOT_FOUND);
//		
//		
//	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		//List<ObjectError> errors = ex.getAllErrors();
		// List<String> messages = new ArrayList<String>();
		Map<String, String> messages = new HashMap<>();

		//errors .forEach(error -> {

		ex.getAllErrors().forEach(error -> {
			FieldError fieldError = (FieldError) error;
			String message = error.getDefaultMessage();
			messages.put(fieldError.getField(), message);
			
			

		});
		
		//more SHort
		
//		errors.forEach(error -> {
//			messages.put(((FieldError) error).getField(), error.getDefaultMessage());
//		});

		return ResponseEntity.badRequest().body(errorStructure.setStatuscode(HttpStatus.BAD_REQUEST.value())
				.setErrorMessage("Invalid Inputs").setRootCause(messages));

	}
}
