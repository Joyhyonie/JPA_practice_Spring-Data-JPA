package com.greedy.practice.product.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.practice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	/* 전달 받은 키워드가 포함된 상품을 조회하는 메소드 */
	@Query("SELECT p FROM product p WHERE p.productName LIKE %:keyword%")
    public Page<Product> findProductByContaining(Pageable pageable, String keyword);

	/* 전달 받은 날짜 이후의 상품을 조회하는 메소드 */
	List<Product> findByDateAfter(Date dateAfter, Sort sort);

}
