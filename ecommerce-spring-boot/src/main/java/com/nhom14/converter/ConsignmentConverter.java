package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.nhom14.dto.ConsignmentDTO;
import com.nhom14.entity.ConsignmentEntity;

public class ConsignmentConverter implements IConverterToDTO<ConsignmentEntity, ConsignmentDTO>, IConverterToEntity<ConsignmentEntity, ConsignmentDTO> {

	@Override
	public ConsignmentEntity toEntity(ConsignmentDTO dto) {
		ConsignmentEntity entity = new ConsignmentEntity();
		entity.setDate(dto.getDate());
		entity.setId(dto.getId());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		return entity;
	}
	public ConsignmentEntity toEntity(ConsignmentDTO dto, ConsignmentEntity entity) {
		entity.setDate(dto.getDate());
		entity.setId(dto.getId());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		return entity;
	}

	@Override
	public List<ConsignmentEntity> toEntityList(List<ConsignmentDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

	@Override
	public ConsignmentDTO toDTO(ConsignmentEntity entity) {
		ConsignmentDTO dto = new ConsignmentDTO();
		
		dto.setDate(entity.getDate());
		dto.setId(entity.getId());
		dto.setPrice(entity.getPrice());
		dto.setProductCode(entity.getProduct().getCode());
		dto.setProductId(entity.getProduct().getId());
		dto.setProductImage(entity.getProduct().getAvatar());
		dto.setProductName(entity.getProduct().getName());
		dto.setQuantity(entity.getQuantity());
		
		return dto;
	}

	@Override
	public List<ConsignmentDTO> toDTOList(List<ConsignmentEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

}
