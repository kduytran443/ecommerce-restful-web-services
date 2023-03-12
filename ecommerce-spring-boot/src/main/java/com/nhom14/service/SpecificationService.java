package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.SpecificationDTO;

public interface SpecificationService {
	
	List<SpecificationDTO> findAllByCategoryCode(String code);
	SpecificationDTO save(SpecificationDTO specificationDTO);
	void delete(SpecificationDTO specificationDTO);
	
}
