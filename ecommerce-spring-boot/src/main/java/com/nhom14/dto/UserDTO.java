package com.nhom14.dto;

public class UserDTO {
	private Long id;
	private String username;
	private String fullname;
	private String email;
	private int phoneNumber;
	private String gender;
	private int brithYear;
	private String password;
	private String role;
	private String avatar;
	private String jwt;
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public UserDTO() {
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBrithYear() {
		return brithYear;
	}

	public void setBrithYear(int brithYear) {
		this.brithYear = brithYear;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", fullname=" + fullname + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", brithYear=" + brithYear + ", password="
				+ password + ", role=" + role + ", avatar=" + avatar + ", jwt=" + jwt + "]";
	}

}
