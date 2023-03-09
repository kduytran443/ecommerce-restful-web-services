package com.nhom14.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.nhom14.entity.id.CartId;

@Entity
@Table(name = "`cart`")
public class CartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CartId cartId;

	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@Column
	private Integer quantity;

	public CartId getCartId() {
		return cartId;
	}

	public void setCartId(CartId cartId) {
		this.cartId = cartId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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

}
