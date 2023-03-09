package com.nhom14.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "`product`")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "nvarchar(256)")
	private String name;

	@Column
	private Integer year;

	@Column
	private Integer status;

	@Column
	private Integer warrantyMonth;

	@Column
	private Integer price;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductImageEntity> productImages;

	@ManyToMany(mappedBy = "favoritedProducts")
	private List<UserEntity> users; // who favorites this product

	@OneToMany(mappedBy = "product")
	private List<ConsigmentEntity> consigments;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_info_id")
	private ProductInfoEntity productInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id")
	private ManufacturerEntity manufacturer;

	@ManyToMany(mappedBy = "products")
	private List<DiscountEntity> discount;

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

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<ProductImageEntity> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImageEntity> productImages) {
		this.productImages = productImages;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<ConsigmentEntity> getConsigments() {
		return consigments;
	}

	public void setConsigments(List<ConsigmentEntity> consigments) {
		this.consigments = consigments;
	}

	public ProductInfoEntity getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfoEntity productInfo) {
		this.productInfo = productInfo;
	}

	public ManufacturerEntity getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerEntity manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<DiscountEntity> getDiscount() {
		return discount;
	}

	public void setDiscount(List<DiscountEntity> discount) {
		this.discount = discount;
	}

}
