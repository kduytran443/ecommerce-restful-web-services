package com.nhom14.dto;

import java.sql.Timestamp;

public class PaymentDTO {
	private Long id;
	private Long totalPrice;
	private String numberAccount;
	private Long paymentTypeId;
	private String paymentTypeName;
	private Long orderId;
	private Timestamp date;
	private String transaction;

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PaymentDTO [id=" + id + ", totalPrice=" + totalPrice + ", numberAccount=" + numberAccount
				+ ", paymentTypeId=" + paymentTypeId + ", paymentTypeName=" + paymentTypeName + ", orderId=" + orderId
				+ ", date=" + date + ", transaction=" + transaction + "]";
	}

}
