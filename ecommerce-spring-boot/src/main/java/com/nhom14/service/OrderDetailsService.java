package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.OrderDetailsDTO;

public interface OrderDetailsService {
	
	List<OrderDetailsDTO> findAllByOrderId(Long orderId);
	OrderDetailsDTO save(OrderDetailsDTO orderDetailsDTO);
	void delete(OrderDetailsDTO orderDetailsDTO);
	List<OrderDetailsDTO> findAllByProductCode(String productCode);
	List<OrderDetailsDTO> findAllByProductId(Long productId);
	
}
