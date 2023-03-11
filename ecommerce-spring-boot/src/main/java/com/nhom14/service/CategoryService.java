package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO findOneByCode(String code);
	List<CategoryDTO> findAll();
	CategoryDTO save(CategoryDTO categoryDTO);
	void delete(CategoryDTO categoryDTO);
	
}
