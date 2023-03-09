package com.nhom14.entity.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductSpecificationsId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "specification_id")
	private Long specificationId;

	@Column(name = "product_id")
	private Long productId;

	public Long getSpecificationId() {
		return specificationId;
	}

	public void setSpecificationId(Long specificationId) {
		this.specificationId = specificationId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
