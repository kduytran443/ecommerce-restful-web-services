package com.nhom14.dto;

import java.sql.Timestamp;

public class ConsignmentDTO {
	private Long id;
	private Integer quantity;
	private Integer price;
	private Timestamp date;
	private Long productId;
	private String productName;
	private String productCode;
	private String productImage;
	private Integer sellProduct;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Integer getSellProduct() {
		return sellProduct;
	}

	public void setSellProduct(Integer sellProduct) {
		this.sellProduct = sellProduct;
	}

}
