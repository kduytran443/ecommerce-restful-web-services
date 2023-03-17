package com.nhom14.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "`payment`")
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long totalPrice;

	@Column(columnDefinition = "nvarchar(64)")
	private String numberAccount;

	@Column(columnDefinition = "nvarchar(320)")
	private String paymentTransaction;

	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentTypeEntity paymentType;

	@OneToOne(mappedBy = "payment")
	private OrderEntity order;

	@Column
	private Timestamp date;

	public String getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(String paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
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

	public PaymentTypeEntity getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypeEntity paymentType) {
		this.paymentType = paymentType;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

}
