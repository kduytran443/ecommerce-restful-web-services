package com.nhom14.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.PaymentConverter;
import com.nhom14.dto.PaymentDTO;
import com.nhom14.entity.OrderEntity;
import com.nhom14.entity.PaymentEntity;
import com.nhom14.entity.PaymentTypeEntity;
import com.nhom14.repository.OrderRepository;
import com.nhom14.repository.PaymentRepository;
import com.nhom14.repository.PaymentTypeRepository;
import com.nhom14.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;

	@Autowired
	private PaymentConverter paymentConverter;

	@Override
	public PaymentDTO findOneByOrderId(Long orderId) {
		OrderEntity orderEntity = orderRepository.findOne(orderId);

		if (orderEntity.getPayment() != null) {
			PaymentEntity paymentEntity = orderEntity.getPayment();

			return paymentConverter.toDTO(paymentEntity);
		}

		return null;
	}

	@Override
	public PaymentDTO save(PaymentDTO paymentDTO) {
		PaymentEntity paymentEntity = null;
		
		if(paymentDTO.getId() != null) {
			paymentEntity = paymentRepository.findOne(paymentDTO.getId());
			paymentEntity = paymentConverter.toEntity(paymentDTO, paymentEntity);
		}
		else {
			paymentEntity = paymentConverter.toEntity(paymentDTO);
			
			OrderEntity orderEntity = orderRepository.findOne(paymentDTO.getOrderId());
			PaymentTypeEntity paymentTypeEntity = paymentTypeRepository.findOne(paymentDTO.getPaymentTypeId());
			
			paymentEntity.setOrder(orderEntity);
			paymentEntity.setPaymentType(paymentTypeEntity);
			
		}
		
		if(paymentEntity != null) {
			paymentEntity = paymentRepository.save(paymentEntity);
			return paymentConverter.toDTO(paymentEntity);
		}
		
		return null;
	}

	@Override
	public void delete(PaymentDTO paymentDTO) {
		paymentRepository.delete(paymentDTO.getId());
	}

}
