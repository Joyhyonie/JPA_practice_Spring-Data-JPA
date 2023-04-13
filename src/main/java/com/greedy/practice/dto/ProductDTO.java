package com.greedy.practice.dto;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.practice.entity.SupplierType;

public class ProductDTO {
	
	private int productNo;
	private String productName;
	private int productPrice;
	private Date date;
	private SupplierType supplierName;
	private String orderableStatus;
	
	public ProductDTO() {}

	public ProductDTO(int productNo, String productName, int productPrice, Date date, SupplierType supplierName, String orderableStatus) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.date = date;
		this.supplierName = supplierName;
		this.orderableStatus = orderableStatus;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SupplierType getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(SupplierType supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getOrderableStatus() {
		return orderableStatus;
	}

	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", date=" + date + ", supplierName=" + supplierName + ", orderableStatus=" + orderableStatus + "]";
	}
	
}
