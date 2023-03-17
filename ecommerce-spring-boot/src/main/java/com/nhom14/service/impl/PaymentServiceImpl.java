package com.nhom14.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.PaymentConverter;
import com.nhom14.dto.PaymentDTO;
import com.nhom14.entity.OrderEntity;
import com.nhom14.entity.PaymentEntity;
import com.nhom14.entity.PaymentTypeEntity;
import com.nhom14.entity.UserEntity;
import com.nhom14.repository.OrderRepository;
import com.nhom14.repository.PaymentRepository;
import com.nhom14.repository.PaymentTypeRepository;
import com.nhom14.repository.UserRepository;
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
	
	@Autowired
	private UserRepository userRepository;
	
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
		OrderEntity orderEntity = orderRepository.findOne(paymentDTO.getOrderId());
		
		if(paymentDTO.getId() != null) {
			paymentEntity = paymentRepository.findOne(paymentDTO.getId());
			paymentEntity = paymentConverter.toEntity(paymentDTO, paymentEntity);
		}
		else {
			paymentEntity = paymentConverter.toEntity(paymentDTO);
			
			if(paymentDTO.getPaymentTypeId() != null) {				
				PaymentTypeEntity paymentTypeEntity = paymentTypeRepository.findOne(paymentDTO.getPaymentTypeId());
				paymentEntity.setPaymentType(paymentTypeEntity);
			}
			
			Date date = new Date();
			paymentEntity.setDate(new Timestamp(date.getTime()));
			
			if(orderEntity.getStatus() == 1) {
				System.out.println("Thanh toán: status "+orderEntity.getStatus());
				orderEntity.setStatus(orderEntity.getStatus() + 1);
			}
			System.out.println("orderEntity id: "+orderEntity.getId());
			paymentEntity.setOrder(orderEntity);
		}
		
		if(paymentEntity != null) {
			paymentEntity = paymentRepository.save(paymentEntity);
			orderEntity.setPayment(paymentEntity);
			orderRepository.save(orderEntity);
			return paymentConverter.toDTO(paymentEntity);
		}
		
		return null;
	}

	@Override
	public void delete(PaymentDTO paymentDTO) {
		paymentRepository.delete(paymentDTO.getId());
	}

	@Override
	public List<PaymentDTO> findAllByUserId(Long userId) {
		UserEntity userEntity = userRepository.findOne(userId);
		List<OrderEntity> orderEntities = userEntity.getOrders();
		
		List<PaymentEntity> paymentEntities = new ArrayList<>();
		for (OrderEntity orderEntity : orderEntities) {
			PaymentEntity paymentEntity = orderEntity.getPayment();
			paymentEntities.add(paymentEntity);
		}

		return paymentConverter.toDTOList(paymentEntities);
	}

}
