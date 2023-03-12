package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nhom14.converter.PaymentTypeConverter;
import com.nhom14.dto.PaymentTypeDTO;
import com.nhom14.repository.PaymentTypeRepository;
import com.nhom14.service.PaymentTypeService;

public class PaymentTypeServiceImpl implements PaymentTypeService {

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private PaymentTypeConverter paymentTypeConverter;
	
	@Override
	public List<PaymentTypeDTO> findAll() {
		return paymentTypeConverter.toDTOList(paymentTypeRepository.findAll());
	}

}
