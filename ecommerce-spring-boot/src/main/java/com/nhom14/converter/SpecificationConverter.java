package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.SpecificationDTO;
import com.nhom14.entity.SpecificationEntity;

@Component
public class SpecificationConverter implements IConverterToEntity<SpecificationEntity, SpecificationDTO>, IConverterToDTO<SpecificationEntity, SpecificationDTO> {

	@Override
	public SpecificationEntity toEntity(SpecificationDTO dto) {
		SpecificationEntity entity = new SpecificationEntity();
		entity.setName(dto.getName());
		return entity;
	}
	
	public SpecificationEntity toEntity(SpecificationDTO dto, SpecificationEntity entity) {
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public List<SpecificationEntity> toEntityList(List<SpecificationDTO> dtos) {
		List<SpecificationEntity> entities = dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
		return entities;
	}

	@Override
	public SpecificationDTO toDTO(SpecificationEntity entity) {
		SpecificationDTO dto = new SpecificationDTO();
		if(entity.getId() != null) dto.setId(entity.getId());
		dto.setName(entity.getName());
		if(entity.getCategory() != null) dto.setCategoryCode(entity.getCategory().getCode());
		return dto;
	}

	@Override
	public List<SpecificationDTO> toDTOList(List<SpecificationEntity> entities) {
		List<SpecificationDTO> dtos = entities.stream().map(item -> toDTO(item)).collect(Collectors.toList());
		return dtos;
	}

}
