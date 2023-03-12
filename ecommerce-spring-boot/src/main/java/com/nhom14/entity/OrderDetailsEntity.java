package com.nhom14.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.nhom14.entity.id.OrderDetailsId;

@Entity
@Table(name = "`order_details`")
public class OrderDetailsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderDetailsId orderDetailsId;

	@ManyToOne
	@MapsId("order_id")
	@JoinColumn(name = "order_id")
	private OrderEntity order;

	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@Column
	private Integer quantity;

	@Column
	private Integer price;

	@Column(columnDefinition = "tinyint")
	private Integer discountPercent;

	public OrderDetailsId getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(OrderDetailsId orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
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

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

}
