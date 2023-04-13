package com.greedy.practice.entity;

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

@Entity(name="product")
@Table(name="TBL_PRODUCT")
@SequenceGenerator(
		name="PRODUCT_SEQUENCE_GENERATOR", 	
		sequenceName="SEQ_PRODUCT_NO", 		
		initialValue=1,						
		allocationSize=1				
		)
public class Product {
	
	@Id
	@Column(name="PRODUCT_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PRODUCT_SEQUENCE_GENERATOR")
	private int productNo;
	
	@Column(name="PRODUCT_NAME", nullable=false)
	private String productName;
	
	@Column(name="PRODUCT_PRICE", nullable=false)
	private int productPrice;
	
	@Column(name="RELEASE_DATE", nullable=false)
	private Date date;
	
	/* enum type을 활용한 필드 */
	@Column(name="SUPPLIER_NAME", nullable=false)
	@Enumerated(EnumType.STRING) // int가 아닌 String 타입으로 DB에 저장할 것
	private SupplierType supplierName;
	
	@Column(name="ORDERABLE_STATUS", nullable=false)
	private String orderableStatus;
	
	public Product() {}

	public Product(int productNo, String productName, int productPrice, Date date, SupplierType supplierName, String orderableStatus) {
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
