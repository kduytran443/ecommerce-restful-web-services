package com.nhom14.dto;

public class SpecificationDTO {
	private Long id;
	private String name;
	private String categoryCode;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SpecificationDTO [id=" + id + ", name=" + name + ", categoryCode=" + categoryCode + "]";
	}

}
