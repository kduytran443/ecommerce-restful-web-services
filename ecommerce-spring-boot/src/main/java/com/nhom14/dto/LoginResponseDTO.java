package com.nhom14.dto;

public class LoginResponseDTO {
	private String jwt;
	
	public LoginResponseDTO(){}
	
	public LoginResponseDTO(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
}
