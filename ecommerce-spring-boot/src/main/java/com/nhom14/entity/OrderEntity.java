package com.nhom14.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "`order`")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Timestamp date;

	@Column
	private Integer deliveryFee;

	@Column
	private Timestamp expectedTime;

	@Column(columnDefinition = "nvarchar(512)")
	private String note;

	@Column(columnDefinition = "tinyint")
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private AddressEntity address;

	@OneToOne
	@JoinColumn(name = "payment_id")
	private PaymentEntity payment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Integer deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Timestamp getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(Timestamp expectedTime) {
		this.expectedTime = expectedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public PaymentEntity getPayment() {
		return payment;
	}

	public void setPayment(PaymentEntity payment) {
		this.payment = payment;
	}

}
