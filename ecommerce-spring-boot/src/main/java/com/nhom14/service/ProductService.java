package com.nhom14.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nhom14.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> findAllByCategoryCode(String code, Pageable pageable);
	List<ProductDTO> findAllByManufacturerCode(String code, Pageable pageable);
	int countByCategoryCode(String code);
	int countByManufacturerCode(String code);
	Long countAll();
}
