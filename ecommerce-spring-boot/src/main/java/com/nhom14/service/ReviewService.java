package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.ReviewDTO;

public interface ReviewService {
	
	List<ReviewDTO> findAllByProductCode(String productCode);
	List<ReviewDTO> findAllByUserId(Long id);
	ReviewDTO save(ReviewDTO reviewDTO);
	void delete(ReviewDTO reviewDTO);
	ReviewDTO findOneByProductCodeAndUsername(String productCode, String username);
	
}
