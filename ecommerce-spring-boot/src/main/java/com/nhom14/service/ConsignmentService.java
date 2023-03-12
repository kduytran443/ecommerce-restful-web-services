package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.ConsignmentDTO;

public interface ConsignmentService {
	
	List<ConsignmentDTO> findAll();
	List<ConsignmentDTO> findAllByProductCode(String productCode);
	ConsignmentDTO save(ConsignmentDTO consignmentDTO);
	void delete(ConsignmentDTO consignmentDTO);
	
}
