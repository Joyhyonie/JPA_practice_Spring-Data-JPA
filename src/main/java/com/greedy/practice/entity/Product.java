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
	
	/* property 접근을 통한 가공을 하는 필드 */
	@Column(name="PRODUCT_PRICE", nullable=false)
	private int productPrice;
	
	@Column(name="RELEASE_DATE", nullable=false)
	private Date releaseDate;
	
	/* enum type을 활용한 필드 */
	@Column(name="SUPPLIER_NAME", nullable=false)
	@Enumerated(EnumType.STRING) // int가 아닌 String 타입으로 DB에 저장할 것
	private SupplierType supplierName;
	
	@Column(name="ORDERABLE_STATUS", nullable=false)
	private String orderableStatus;
	
	public Product() {}

	public Product(int productNo, String productName, int productPrice, Date releaseDate, SupplierType supplierName, String orderableStatus) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.releaseDate = releaseDate;
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

//	@Access(AccessType.PROPERTY)
	public int getProductPrice() {
		
		System.out.println("getProductPrice()를 이용한 access 확인! 수수료 & 부가세 적용 성공");
		
		/* 상품 수수료 & 부가세 */
		int charge = (int) (productPrice * 0.1);
		int tax = (int) (productPrice * 0.05);
		
		return productPrice + charge + tax ;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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
				+ ", releaseDate=" + releaseDate + ", supplierName=" + supplierName + ", orderableStatus=" + orderableStatus + "]";
	}
	
}
