package com.greedy.practice.product.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.practice.dto.ProductDTO;
import com.greedy.practice.entity.Product;
import com.greedy.practice.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	
	public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 전체 상품 */
	public List<ProductDTO> findProductList() {
		
		List<Product> productList = productRepository.findAll(Sort.by("productNo"));
		
		return productList.stream().map(menu -> modelMapper.map(menu, ProductDTO.class)).collect(Collectors.toList());
	}
	
	/* 전체 상품 (Paging ver) */
	public Page<ProductDTO> findProductList(Pageable pageable) {
		
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1, 
				   pageable.getPageSize(),										
				   Sort.by("productNo").descending());
		
		Page<Product> productList = productRepository.findAll(pageable);
		
		return productList.map(product -> modelMapper.map(product, ProductDTO.class));
	}
	
	
	/* 상품 검색 */
	public Page<ProductDTO> searchProductList(Pageable pageable, String keyword) {
		
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1, 
				   pageable.getPageSize(),										
				   Sort.by("productNo").descending());
		
		Page<Product> productList = productRepository.findProductByContaining(pageable, keyword);
		log.info("productList : {}", productList);
		
		return productList.map(menu -> modelMapper.map(menu, ProductDTO.class));
	}
	
	/* 등록일이 입력된 날짜 이후인 상품 조회 */
	public List<ProductDTO> searchDateAfterProductList(Date dateAfter) {
		
		List<Product> productList = productRepository.findByDateAfter(dateAfter, Sort.by("date").descending());
		log.info("productList : {}", productList);
		
		return productList.stream().map(menu -> modelMapper.map(menu, ProductDTO.class)).collect(Collectors.toList());
	}
	
	
	/* 상품 등록 */
	@Transactional
	public void saveNewProduct(ProductDTO newProduct) {
		
		/* 등록일 가공 */
		newProduct.setDate(new java.sql.Date(System.currentTimeMillis()));
		
		/* 상품원가 가공 (상품 수수료 & 부가세) */
		int charge = (int) (newProduct.getProductPrice() * 0.1);
		int tax = (int) (newProduct.getProductPrice() * 0.05);
		newProduct.setProductPrice(newProduct.getProductPrice() + charge + tax);
		
		productRepository.save(modelMapper.map(newProduct, Product.class));
		
	}

	/* 상품 수정 */
	@Transactional
	public void modifyProduct(ProductDTO product) {
		
		Product foundProduct = productRepository.findById(product.getProductNo()).orElseThrow(IllegalArgumentException::new);
		
		/* 상품원가 가공 (상품 수수료 & 부가세) */
		int charge = (int) (product.getProductPrice() * 0.1);
		int tax = (int) (product.getProductPrice() * 0.05);
		
		foundProduct.setProductName(product.getProductName());
		foundProduct.setProductPrice(product.getProductPrice() + charge + tax);
		foundProduct.setSupplierName(product.getSupplierName());
		foundProduct.setOrderableStatus(product.getOrderableStatus());
		
	}
	
	/* 수정된 하나의 상품을 조회 */
	public ProductDTO findProductByCode(int productNo) {
		
		Product product = productRepository.findById(productNo).orElseThrow(IllegalArgumentException::new);
		
		return modelMapper.map(product, ProductDTO.class);
	}

	/* 상품 삭제 */
	@Transactional
	public void deleteProduct(ProductDTO product) {
		
		productRepository.deleteById(product.getProductNo());
		
	}

	

	


	

}
