package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.OrderDetailsConverter;
import com.nhom14.dto.OrderDetailsDTO;
import com.nhom14.entity.OrderDetailsEntity;
import com.nhom14.entity.OrderEntity;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.id.OrderDetailsId;
import com.nhom14.repository.OrderDetailsRepository;
import com.nhom14.repository.OrderRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private OrderDetailsConverter orderDetailsConverter;

	@Override
	public List<OrderDetailsDTO> findAllByOrderId(Long orderId) {
		OrderEntity orderEntity = orderRepository.findOne(orderId);
		List<OrderDetailsEntity> orderDetailsEntities = orderDetailsRepository.findAllByOrder(orderEntity);

		return orderDetailsConverter.toDTOList(orderDetailsEntities);
	}

	@Override
	public OrderDetailsDTO save(OrderDetailsDTO orderDetailsDTO) {
		ProductEntity productEntity = productRepository.findOne(orderDetailsDTO.getProductId());
		OrderEntity orderEntity = orderRepository.findOne(orderDetailsDTO.getOrderId());

		OrderDetailsEntity orderDetailsEntity = orderDetailsRepository.findOneByOrderAndProduct(orderEntity,
				productEntity);
		
		if(orderDetailsEntity != null) { //edit
			orderDetailsEntity = orderDetailsConverter.toEntity(orderDetailsDTO, orderDetailsEntity);
		}
		else {
			orderDetailsEntity = orderDetailsConverter.toEntity(orderDetailsDTO);
			orderDetailsEntity.setOrder(orderEntity);
			orderDetailsEntity.setProduct(productEntity);
			
			OrderDetailsId orderDetailsId = new OrderDetailsId();
			orderDetailsId.setOrderId(orderEntity.getId());
			orderDetailsId.setProductId(productEntity.getId());
			
			orderDetailsEntity.setOrderDetailsId(orderDetailsId);
		}
		
		if(orderDetailsEntity != null) {
			orderDetailsEntity = orderDetailsRepository.save(orderDetailsEntity);
			return orderDetailsConverter.toDTO(orderDetailsEntity);
		}
		
		return null;
	}

	@Override
	public void delete(OrderDetailsDTO orderDetailsDTO) {
		ProductEntity productEntity = productRepository.findOne(orderDetailsDTO.getProductId());
		OrderEntity orderEntity = orderRepository.findOne(orderDetailsDTO.getOrderId());

		OrderDetailsEntity orderDetailsEntity = orderDetailsRepository.findOneByOrderAndProduct(orderEntity,
				productEntity);
		
		orderDetailsRepository.delete(orderDetailsEntity);
	}

	@Override
	public List<OrderDetailsDTO> findAllByProductCode(String productCode) {
		ProductEntity productEntity = productRepository.findOneByCode(productCode);
		List<OrderDetailsEntity> orderDetailsEntities = orderDetailsRepository.findAllByProduct(productEntity);
		return orderDetailsConverter.toDTOList(orderDetailsEntities);
	}

	@Override
	public List<OrderDetailsDTO> findAllByProductId(Long productId) {
		ProductEntity productEntity = productRepository.findOne(productId);
		List<OrderDetailsEntity> orderDetailsEntities = orderDetailsRepository.findAllByProduct(productEntity);
		return orderDetailsConverter.toDTOList(orderDetailsEntities);
	}

}
