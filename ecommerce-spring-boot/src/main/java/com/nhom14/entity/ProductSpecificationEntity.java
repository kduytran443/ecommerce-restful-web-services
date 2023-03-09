package com.nhom14.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.nhom14.entity.id.ProductSpecificationsId;

@Entity
@Table(name = "`product_specification`")
public class ProductSpecificationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductSpecificationsId productSpecificationsId;

	@ManyToOne
	@MapsId("specification_id")
	@JoinColumn(name = "specification_id")
	private SpecificationEntity specification;

	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@Column(columnDefinition = "nvarchar(512)")
	private String content;

	public ProductSpecificationsId getProductSpecificationsId() {
		return productSpecificationsId;
	}

	public void setProductSpecificationsId(ProductSpecificationsId productSpecificationsId) {
		this.productSpecificationsId = productSpecificationsId;
	}

	public SpecificationEntity getSpecification() {
		return specification;
	}

	public void setSpecification(SpecificationEntity specification) {
		this.specification = specification;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
