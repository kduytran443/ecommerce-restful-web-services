package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.PaymentDTO;

public interface PaymentService {

	PaymentDTO findOneByOrderId(Long orderId);
	List<PaymentDTO> findAllByUserId(Long userId);

	PaymentDTO save(PaymentDTO paymentDTO);

	void delete(PaymentDTO paymentDTO);
}
