package com.nhom14.dto;

import java.sql.Timestamp;

public class OrderDTO {
	private Long id;
	private Timestamp date;
	private Integer deliveryFee;
	private Timestamp expectedTime;
	private String note;
	private Integer status;
	private AddressDTO address;
	private PaymentDTO payment;
	private Long userId;
	private String username;
	private String transaction;

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", date=" + date + ", deliveryFee=" + deliveryFee + ", expectedTime="
				+ expectedTime + ", note=" + note + ", status=" + status + ", address=" + address + ", payment="
				+ payment + ", userId=" + userId + ", username=" + username + "]";
	}

}
