package com.nhom14.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.ManufacturerConverter;
import com.nhom14.dto.ManufacturerDTO;
import com.nhom14.entity.ManufacturerEntity;
import com.nhom14.entity.ProductEntity;
import com.nhom14.repository.ManufacturerRepository;
import com.nhom14.repository.ProductRepository;
import com.nhom14.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private ManufacturerConverter manufacturerConverter;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ManufacturerDTO> findAll() {
		return manufacturerConverter.toDTOList(manufacturerRepository.findAll().stream()
				.filter(item -> item.getStatus() == 1).collect(Collectors.toList()));
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
			manufacturerEntity = manufacturerRepository.findOne(manufacturerDTO.getId());
			
			ManufacturerEntity checkManufacturerEntity = manufacturerRepository.findOneByCode(manufacturerDTO.getCode());
			if(!checkManufacturerEntity.getId().equals(manufacturerEntity.getId())) {
				throw new RuntimeException("Mã code đã tồn tại");
			}
			
			manufacturerEntity = manufacturerConverter.toEntity(manufacturerDTO, manufacturerEntity);
		} else {
			ManufacturerEntity checkManufacturerEntity = manufacturerRepository.findOneByCode(manufacturerDTO.getCode());
			
			if(checkManufacturerEntity != null) {
				throw new RuntimeException("Mã code đã tồn tại");
			}
			
			manufacturerEntity = manufacturerConverter.toEntity(manufacturerDTO);
			manufacturerEntity.setStatus(1);
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

	@Override
	public ManufacturerDTO findOneByProductCode(String code) {

		ProductEntity productEntity = productRepository.findOneByCode(code);
		ManufacturerEntity manufacturerEntity = productEntity.getManufacturer();
		if (manufacturerEntity != null) {
			return manufacturerConverter.toDTO(manufacturerEntity);
		}

		return null;
	}

}
