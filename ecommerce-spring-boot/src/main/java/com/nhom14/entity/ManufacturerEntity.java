package com.nhom14.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "`manufacturer`")
public class ManufacturerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String code;

	@Column(columnDefinition = "nvarchar(128)")
	private String name;

	@Column(columnDefinition = "TEXT")
	private String logo;

	@Column(columnDefinition = "varchar(512)")
	private String googleMap;

	@Column(columnDefinition = "tinyint")
	private Integer status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
	private List<ProductEntity> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getGoogleMap() {
		return googleMap;
	}

	public void setGoogleMap(String googleMap) {
		this.googleMap = googleMap;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

}
