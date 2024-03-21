package com.example.ProductProject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ProductProject.entity.User;
import com.example.ProductProject.utility.ResponseStructure;
@Service
public interface UserService {

	public ResponseEntity<ResponseStructure<User>> saveUser(User user);
}
