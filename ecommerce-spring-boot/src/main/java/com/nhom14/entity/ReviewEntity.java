package com.nhom14.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.nhom14.entity.id.ReviewId;

@Entity
@Table(name = "`review`")
public class ReviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReviewId reviewId;

	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@Column
	private Timestamp date;

	@Column(columnDefinition = "nvarchar(512)")
	private String content;

	public ReviewId getReviewId() {
		return reviewId;
	}

	public void setReviewId(ReviewId reviewId) {
		this.reviewId = reviewId;
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
