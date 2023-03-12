package com.nhom14.service;

import java.util.List;

import com.nhom14.dto.ManufacturerDTO;

public interface ManufacturerService {
	
	List<ManufacturerDTO> findAll();
	ManufacturerDTO findOneByCode(String code);
	ManufacturerDTO save(ManufacturerDTO manufacturerDTO);
	void delete(ManufacturerDTO manufacturerDTO);
	
}
