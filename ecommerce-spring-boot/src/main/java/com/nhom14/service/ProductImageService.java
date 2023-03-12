package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.ProductImageDTO;

public interface ProductImageService {
	
	List<ProductImageDTO> findAllByProductCode(String productCode);

	ProductImageDTO save(ProductImageDTO productImageDTO);

	void delete(ProductImageDTO productImageDTO);
	
}
