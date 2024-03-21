package com.example.ProductProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProductProject.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
