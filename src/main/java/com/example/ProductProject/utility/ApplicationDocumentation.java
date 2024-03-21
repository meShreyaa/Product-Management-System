package com.example.ProductProject.utility;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {

	
	Contact contact() {
		return new Contact().name("Shreya")
				.url("shreya.in")
				.email("bshreya834@gmail.com");
	}
	
	@Bean
	Info info() {
		return new Info().title("Product Management SYstem")
				.description("RESTful API with basic CRUD operation.")
				.version("v1").contact(contact());
		//for add liscence we can use .liscence()
	}
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
}
