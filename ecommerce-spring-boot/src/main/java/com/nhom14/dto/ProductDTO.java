package com.nhom14.dto;

import java.util.List;

public class ProductDTO {
	private Long id;
	private String name;
	private String code;
	private Integer year;
	private Integer status;
	private Integer warrantyMonth;
	private Integer price;
	private String avatar;
	private String categoryCode;
	private String categoryName;
	private List<ProductImageDTO> productImages;
	private List<ProductSpecificationDTO> productSpecifications;
	private Long favorited;
	private Integer stock;
	private ProductInfoDTO productInfo;
	private ManufacturerDTO manufacturer;
	private List<DiscountDTO> discounts;

	public List<ProductSpecificationDTO> getProductSpecifications() {
		return productSpecifications;
	}

	public void setProductSpecifications(List<ProductSpecificationDTO> productSpecifications) {
		this.productSpecifications = productSpecifications;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getWarrantyMonth() {
		return warrantyMonth;
	}

	public void setWarrantyMonth(Integer warrantyMonth) {
		this.warrantyMonth = warrantyMonth;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ProductImageDTO> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImageDTO> productImages) {
		this.productImages = productImages;
	}

	public Long getFavorited() {
		return favorited;
	}

	public void setFavorited(Long favorited) {
		this.favorited = favorited;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public ProductInfoDTO getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfoDTO productInfo) {
		this.productInfo = productInfo;
	}

	public ManufacturerDTO getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerDTO manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<DiscountDTO> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<DiscountDTO> discounts) {
		this.discounts = discounts;
	}

}
