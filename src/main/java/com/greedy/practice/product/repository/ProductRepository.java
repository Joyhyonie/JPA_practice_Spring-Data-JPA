package com.greedy.practice.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.practice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
