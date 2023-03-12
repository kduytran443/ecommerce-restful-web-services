package com.nhom14.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom14.converter.SpecificationConverter;
import com.nhom14.dto.SpecificationDTO;
import com.nhom14.entity.CategoryEntity;
import com.nhom14.entity.SpecificationEntity;
import com.nhom14.repository.CategoryRepository;
import com.nhom14.repository.SpecificationRepository;
import com.nhom14.service.SpecificationService;

@Service
public class SpecificationServiceImpl implements SpecificationService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SpecificationConverter specificationConverter;
	
	@Autowired
	private SpecificationRepository specificationRepository;
	
	@Override
	public List<SpecificationDTO> findAllByCategoryCode(String code) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);
		
		return specificationConverter.toDTOList(categoryEntity.getSpecifications());
	}

	@Override
	public SpecificationDTO save(SpecificationDTO specificationDTO) {
		SpecificationEntity specificationEntity = null;
		if(specificationDTO.getId() != null) { //edit
			specificationEntity = specificationRepository.findOne(specificationDTO.getId());
			specificationEntity = specificationConverter.toEntity(specificationDTO, specificationEntity);
		}
		else {
			specificationEntity = specificationConverter.toEntity(specificationDTO);
			
			CategoryEntity categoryEntity = categoryRepository.findOneByCode(specificationDTO.getCategoryCode());
			specificationEntity.setCategory(categoryEntity);
		}
		
		specificationEntity = specificationRepository.save(specificationEntity);
		
		return specificationConverter.toDTO(specificationEntity);
	}

	@Override
	public void delete(SpecificationDTO specificationDTO) {
		specificationRepository.delete(specificationDTO.getId());
	}

}
