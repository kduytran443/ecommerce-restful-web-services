package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.ManufacturerDTO;
import com.nhom14.entity.ManufacturerEntity;

@Component
public class ManufacturerConverter implements IConverterToEntity<ManufacturerEntity, ManufacturerDTO>, IConverterToDTO<ManufacturerEntity, ManufacturerDTO> {

	@Override
	public ManufacturerDTO toDTO(ManufacturerEntity entity) {
		ManufacturerDTO dto = new ManufacturerDTO();
		
		dto.setCode(entity.getCode());
		dto.setId(entity.getId());
		dto.setLogo(entity.getLogo());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());
		
		return dto;
	}

	@Override
	public List<ManufacturerDTO> toDTOList(List<ManufacturerEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public ManufacturerEntity toEntity(ManufacturerDTO dto) {
		ManufacturerEntity entity = new ManufacturerEntity();
		entity.setCode(dto.getCode());
		entity.setId(dto.getId());
		entity.setLogo(dto.getLogo());
		entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
		return entity;
	}
	public ManufacturerEntity toEntity(ManufacturerDTO dto, ManufacturerEntity entity) {
		entity.setCode(dto.getCode());
		entity.setId(dto.getId());
		entity.setLogo(dto.getLogo());
		entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
		return entity;
	}

	@Override
	public List<ManufacturerEntity> toEntityList(List<ManufacturerDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}
}
