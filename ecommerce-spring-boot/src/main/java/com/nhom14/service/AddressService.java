package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.AddressDTO;

public interface AddressService {
	
	List<AddressDTO> findAllByUsername(String username);
	List<AddressDTO> findAllByUserId(Long userId);
	AddressDTO save(AddressDTO addressDTO);
	void delete(AddressDTO addressDTO);
	
}
