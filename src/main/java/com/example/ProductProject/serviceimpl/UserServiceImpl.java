package com.example.ProductProject.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ProductProject.entity.User;
import com.example.ProductProject.repository.UserRepository;
import com.example.ProductProject.service.UserService;
import com.example.ProductProject.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private ResponseStructure<User> responseStructure;
	
	

	public UserServiceImpl(UserRepository userRepository, ResponseStructure<User> responseStructure) {
		this.userRepository = userRepository;
		this.responseStructure = responseStructure;
	}



	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		User uniqueUser=userRepository.save(user);
		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value()).setMessage("Unique User Added").setData(uniqueUser));
	}

}
