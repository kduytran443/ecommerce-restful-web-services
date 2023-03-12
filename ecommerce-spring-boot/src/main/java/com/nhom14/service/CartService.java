package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.CartDTO;

public interface CartService {
	
	List<CartDTO> findAllByUserId(Long userId);
	CartDTO findOneByUserIdAndProductCode(CartDTO cartDTO);
	CartDTO save(CartDTO cartDTO);
	void delete(CartDTO cartDTO);
	
}
