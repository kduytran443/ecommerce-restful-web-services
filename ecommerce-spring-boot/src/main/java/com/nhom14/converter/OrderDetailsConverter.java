package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.OrderDetailsDTO;
import com.nhom14.entity.OrderDetailsEntity;

@Component
public class OrderDetailsConverter implements IConverterToDTO<OrderDetailsEntity, OrderDetailsDTO>, IConverterToEntity<OrderDetailsEntity, OrderDetailsDTO> {

	@Override
	public OrderDetailsEntity toEntity(OrderDetailsDTO dto) {
		OrderDetailsEntity entity = new OrderDetailsEntity();
		
		entity.setDiscountPercent(dto.getDiscountPercent());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		
		return entity;
	}
	public OrderDetailsEntity toEntity(OrderDetailsDTO dto, OrderDetailsEntity entity) {
		
		entity.setDiscountPercent(dto.getDiscountPercent());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		
		return entity;
	}

	@Override
	public List<OrderDetailsEntity> toEntityList(List<OrderDetailsDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

	@Override
	public OrderDetailsDTO toDTO(OrderDetailsEntity entity) {
		OrderDetailsDTO dto = new OrderDetailsDTO();
		dto.setDiscountPercent(entity.getDiscountPercent());
		dto.setPrice(entity.getPrice());
		dto.setProductCode(entity.getProduct().getCode());
		dto.setProductId(entity.getProduct().getId());
		dto.setQuantity(entity.getQuantity());
		dto.setProductAvatar(entity.getProduct().getAvatar());
		dto.setProductName(entity.getProduct().getName());
		return dto;
	}

	@Override
	public List<OrderDetailsDTO> toDTOList(List<OrderDetailsEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

}
