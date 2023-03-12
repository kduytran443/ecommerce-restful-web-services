package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.ProductSpecificationDTO;

public interface ProductSpecificationService {

	List<ProductSpecificationDTO> findAllByProductCode(String productCode);

	ProductSpecificationDTO save(ProductSpecificationDTO productSpecificationDTO);

	void delete(ProductSpecificationDTO productSpecificationDTO);

}
