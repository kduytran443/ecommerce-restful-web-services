package com.nhom14.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.OrderConverter;
import com.nhom14.dto.OrderDTO;
import com.nhom14.entity.AddressEntity;
import com.nhom14.entity.OrderEntity;
import com.nhom14.entity.PaymentEntity;
import com.nhom14.entity.UserEntity;
import com.nhom14.repository.AddressRepository;
import com.nhom14.repository.OrderRepository;
import com.nhom14.repository.PaymentRepository;
import com.nhom14.repository.UserRepository;
import com.nhom14.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderConverter orderConverter;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<OrderDTO> findAllByOrderByIdDesc() {
		
		List<OrderEntity> orderEntities = orderRepository.findAllByOrderByIdDesc();
		
		if(orderEntities != null) {
			return orderConverter.toDTOList(orderEntities);
		}
		
		return null;
	}

	@Override
	public List<OrderDTO> findAllByUserId(Long userId) {
		UserEntity userEntity = userRepository.findOne(userId);
		List<OrderEntity> orderEntities = userEntity.getOrders();
		return orderConverter.toDTOList(orderEntities);
	}

	@Override
	public OrderDTO save(OrderDTO orderDTO) {
		OrderEntity orderEntity = null;
		if (orderDTO.getId() != null) {
			orderEntity = orderConverter.toEntity(orderDTO);

			if (orderDTO.getAddress() != null) {
				AddressEntity addressEntity = addressRepository.findOne(orderDTO.getAddress().getId());
				orderEntity.setAddress(addressEntity);
			}

			if (orderDTO.getPayment() != null) {
				PaymentEntity paymentEntity = paymentRepository.findOne(orderDTO.getPayment().getId());
				orderEntity.setPayment(paymentEntity);
			}
		} else {
			
			System.out.println("***Order: "+orderDTO);
			
			orderEntity = orderConverter.toEntity(orderDTO);
			AddressEntity addressEntity = addressRepository.findOne(orderDTO.getAddress().getId());
			orderEntity.setAddress(addressEntity);
			if(orderDTO.getPayment() != null) {
				PaymentEntity paymentEntity = paymentRepository.findOne(orderDTO.getPayment().getId());
				orderEntity.setPayment(paymentEntity);
			}
			UserEntity userEntity = userRepository.findOne(orderDTO.getUserId());
			orderEntity.setUser(userEntity);

			Date date = new Date();
			orderEntity.setDate(new Timestamp(date.getTime()));

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 2);
			Date currentDatePlusOne = c.getTime();

			orderEntity.setExpectedTime(new Timestamp(currentDatePlusOne.getTime()));
			orderEntity.setStatus(1);
			orderEntity.setDeliveryFee(5);
		}

		if (orderEntity != null) {
			orderEntity = orderRepository.save(orderEntity);
			return orderConverter.toDTO(orderEntity);
		}

		return null;
	}

	@Override
	public void delete(OrderDTO orderDTO) {
		orderRepository.delete(orderDTO.getId());
	}

	@Override
	public OrderDTO findOneById(Long id) {
		return orderConverter.toDTO(orderRepository.findOne(id));
	}

	@Override
	public OrderDTO updateStatus(Long orderId, int status) {

		OrderEntity orderEntity = orderRepository.findOne(orderId);
		
		if(orderEntity != null) {
			orderEntity.setStatus(status);
			orderEntity = orderRepository.save(orderEntity);
			return orderConverter.toDTO(orderEntity);
		}
		
		return null;
	}

}
