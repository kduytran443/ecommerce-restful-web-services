package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.PaymentTypeDTO;
import com.nhom14.entity.PaymentTypeEntity;

@Component
public class PaymentTypeConverter implements IConverterToEntity<PaymentTypeEntity, PaymentTypeDTO>, IConverterToDTO<PaymentTypeEntity, PaymentTypeDTO> {

	@Override
	public PaymentTypeDTO toDTO(PaymentTypeEntity entity) {
		PaymentTypeDTO dto = new PaymentTypeDTO();
		dto.setCode(entity.getCode());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public List<PaymentTypeDTO> toDTOList(List<PaymentTypeEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public PaymentTypeEntity toEntity(PaymentTypeDTO dto) {
		PaymentTypeEntity entity = new PaymentTypeEntity();
		
		entity.setCode(dto.getCode());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		
		return entity;
	}
	public PaymentTypeEntity toEntity(PaymentTypeDTO dto, PaymentTypeEntity entity) {
		entity.setCode(dto.getCode());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public List<PaymentTypeEntity> toEntityList(List<PaymentTypeDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}
	
}
