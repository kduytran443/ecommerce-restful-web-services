package com.nhom14.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

public class PageableDTO<T> {
	private Pageable pageable;
	private List<T> data;
	private int countAll;

	public int getCountAll() {
		return countAll;
	}

	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
