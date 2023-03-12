package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.DiscountDTO;
import com.nhom14.entity.DiscountEntity;

@Component
public class DiscountConverter
		implements IConverterToDTO<DiscountEntity, DiscountDTO>, IConverterToEntity<DiscountEntity, DiscountDTO> {

	@Override
	public DiscountDTO toDTO(DiscountEntity entity) {
		DiscountDTO dto = new DiscountDTO();

		dto.setDiscountPercent(entity.getDiscountPercent());
		dto.setEndTime(entity.getEndTime());
		dto.setStartTime(entity.getStartTime());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStartTime(entity.getStartTime());

		return dto;
	}

	@Override
	public List<DiscountDTO> toDTOList(List<DiscountEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public DiscountEntity toEntity(DiscountDTO dto) {
		DiscountEntity entity = new DiscountEntity();
		entity.setDiscountPercent(dto.getDiscountPercent());
		entity.setEndTime(dto.getEndTime());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setStartTime(dto.getStartTime());
		return entity;
	}
	public DiscountEntity toEntity(DiscountDTO dto, DiscountEntity entity) {
		entity.setDiscountPercent(dto.getDiscountPercent());
		entity.setEndTime(dto.getEndTime());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setStartTime(dto.getStartTime());
		return entity;
	}

	@Override
	public List<DiscountEntity> toEntityList(List<DiscountDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

}
