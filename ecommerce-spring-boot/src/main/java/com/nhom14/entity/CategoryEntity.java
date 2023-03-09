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
@Table(name = "`category`")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "char(128)")
	private String code;

	@Column(columnDefinition = "nvarchar(128)")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<SpecificationEntity> specification;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
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

	public List<SpecificationEntity> getSpecification() {
		return specification;
	}

	public void setSpecification(List<SpecificationEntity> specification) {
		this.specification = specification;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

}
