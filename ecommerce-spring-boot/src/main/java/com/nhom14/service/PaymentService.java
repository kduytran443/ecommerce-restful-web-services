package com.nhom14.service;

import com.nhom14.dto.PaymentDTO;

public interface PaymentService {

	PaymentDTO findOneByOrderId(Long orderId);

	PaymentDTO save(PaymentDTO paymentDTO);

	void delete(PaymentDTO paymentDTO);
}
