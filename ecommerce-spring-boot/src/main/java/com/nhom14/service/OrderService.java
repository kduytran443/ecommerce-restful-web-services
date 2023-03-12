package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.OrderDTO;

public interface OrderService {
	List<OrderDTO> findAllByOrderByIdDesc();

	List<OrderDTO> findAllByUserId(Long userId);
	OrderDTO findOneById(Long id);

	OrderDTO save(OrderDTO orderDTO);

	void delete(OrderDTO orderDTO);
}
