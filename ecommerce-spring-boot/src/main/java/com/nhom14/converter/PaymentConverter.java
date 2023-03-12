package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.PaymentDTO;
import com.nhom14.entity.PaymentEntity;

@Component
public class PaymentConverter implements IConverterToDTO<PaymentEntity, PaymentDTO>, IConverterToEntity<PaymentEntity, PaymentDTO> {
	
	@Override
	public PaymentEntity toEntity(PaymentDTO dto) {
		PaymentEntity entity = new PaymentEntity();
		
		entity.setDate(dto.getDate());
		entity.setId(dto.getId());
		entity.setNumberAccount(dto.getNumberAccount());
		entity.setTotalPrice(dto.getTotalPrice());
		
		return entity;
	}
	public PaymentEntity toEntity(PaymentDTO dto, PaymentEntity entity) {
		
		entity.setDate(dto.getDate());
		entity.setId(dto.getId());
		entity.setNumberAccount(dto.getNumberAccount());
		entity.setTotalPrice(dto.getTotalPrice());
		
		return entity;
	}

	@Override
	public List<PaymentEntity> toEntityList(List<PaymentDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

	@Override
	public PaymentDTO toDTO(PaymentEntity entity) {
		PaymentDTO dto = new PaymentDTO();
		
		dto.setDate(entity.getDate());
		dto.setId(entity.getId());
		dto.setNumberAccount(entity.getNumberAccount());
		dto.setTotalPrice(entity.getTotalPrice());
		dto.setPaymentTypeId(entity.getPaymentType().getId());
		dto.setPaymentTypeName(entity.getPaymentType().getName());
		
		return dto;
	}

	@Override
	public List<PaymentDTO> toDTOList(List<PaymentEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

}
