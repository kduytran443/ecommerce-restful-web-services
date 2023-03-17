package com.nhom14.dto;

public class OrderMessageDTO {
	private Long orderId;
	private MessageType type;

	public enum MessageType {
		PROCESS_ORDER
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}
