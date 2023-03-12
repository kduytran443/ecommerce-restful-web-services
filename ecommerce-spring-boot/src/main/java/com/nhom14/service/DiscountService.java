package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.DiscountDTO;

public interface DiscountService {

	List<DiscountDTO> findAll();
	
	List<DiscountDTO> findAllActive();

	List<DiscountDTO> findOneByProductCode(String productCode);

	DiscountDTO findOneById(Long id);

	DiscountDTO save(DiscountDTO discountDTO);

	void delete(DiscountDTO discountDTO);

}
