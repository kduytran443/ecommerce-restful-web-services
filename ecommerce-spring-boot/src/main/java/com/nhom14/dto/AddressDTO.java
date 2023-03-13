package com.nhom14.dto;

public class AddressDTO {
	private Long id;
	private String details;
	private String username;
	private Long userId;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AddressDTO [id=" + id + ", details=" + details + ", username=" + username + ", userId=" + userId
				+ ", status=" + status + "]";
	}

}
