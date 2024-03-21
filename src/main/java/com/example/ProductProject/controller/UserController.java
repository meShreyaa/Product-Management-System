package com.example.ProductProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ProductProject.entity.User;
import com.example.ProductProject.serviceimpl.UserServiceImpl;
import com.example.ProductProject.utility.ResponseStructure;

import jakarta.validation.Valid;
@Controller
public class UserController {

	private UserServiceImpl userService;

	public UserController(UserServiceImpl userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody @Valid User user){
		return userService.saveUser(user);
	}
}
