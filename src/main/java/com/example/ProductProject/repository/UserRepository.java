package com.example.ProductProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProductProject.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
