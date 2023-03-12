package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ManufacturerConverter;
import com.nhom14.dto.ManufacturerDTO;
import com.nhom14.entity.ManufacturerEntity;
import com.nhom14.repository.ManufacturerRepository;
import com.nhom14.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private ManufacturerConverter manufacturerConverter;

	@Override
	public List<ManufacturerDTO> findAll() {
		return manufacturerConverter.toDTOList(manufacturerRepository.findAll());
	}

	@Override
	public ManufacturerDTO findOneByCode(String code) {
		ManufacturerEntity manufacturerEntity = manufacturerRepository.findOneByCode(code);
		if (manufacturerEntity != null) {
			return manufacturerConverter.toDTO(manufacturerEntity);
		}
		return null;
	}

	@Override
	public ManufacturerDTO save(ManufacturerDTO manufacturerDTO) {
		ManufacturerEntity manufacturerEntity = null;
		if (manufacturerDTO.getId() != null) {
			manufacturerEntity = manufacturerRepository.findOneByCode(manufacturerDTO.getCode());
			manufacturerEntity = manufacturerConverter.toEntity(manufacturerDTO, manufacturerEntity);
		} else {
			manufacturerEntity = manufacturerConverter.toEntity(manufacturerDTO);
		}

		if (manufacturerEntity != null) {
			manufacturerEntity = manufacturerRepository.save(manufacturerEntity);
			return manufacturerConverter.toDTO(manufacturerEntity);
		}

		return null;
	}

	@Override
	public void delete(ManufacturerDTO manufacturerDTO) {
		ManufacturerEntity manufacturerEntity = manufacturerRepository.findOneByCode(manufacturerDTO.getCode());
		if (manufacturerEntity != null) {
			manufacturerEntity.setStatus(0);
			manufacturerRepository.save(manufacturerEntity);
		}
	}

}
