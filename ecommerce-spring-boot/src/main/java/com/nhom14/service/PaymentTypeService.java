package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.PaymentTypeDTO;

public interface PaymentTypeService {
	
	List<PaymentTypeDTO> findAll();
	
}
