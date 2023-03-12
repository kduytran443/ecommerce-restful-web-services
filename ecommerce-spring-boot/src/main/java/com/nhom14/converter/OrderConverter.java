package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhom14.dto.OrderDTO;
import com.nhom14.entity.OrderEntity;

@Component
public class OrderConverter
		implements IConverterToDTO<OrderEntity, OrderDTO>, IConverterToEntity<OrderEntity, OrderDTO> {
	
	@Autowired
	private AddressConverter addressConverter;
	
	@Autowired
	private PaymentConverter paymentConverter;
	
	@Override
	public OrderEntity toEntity(OrderDTO dto) {
		OrderEntity entity = new OrderEntity();
		entity.setDate(dto.getDate());
		entity.setDeliveryFee(dto.getDeliveryFee());
		entity.setExpectedTime(dto.getExpectedTime());
		entity.setId(dto.getId());
		entity.setNote(dto.getNote());
		entity.setStatus(dto.getStatus());

		return entity;
	}
	public OrderEntity toEntity(OrderDTO dto, OrderEntity entity) {
		entity.setDate(dto.getDate());
		entity.setDeliveryFee(dto.getDeliveryFee());
		entity.setExpectedTime(dto.getExpectedTime());
		entity.setId(dto.getId());
		entity.setNote(dto.getNote());
		entity.setStatus(dto.getStatus());

		return entity;
	}

	@Override
	public List<OrderEntity> toEntityList(List<OrderDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

	@Override
	public OrderDTO toDTO(OrderEntity entity) {
		OrderDTO dto = new OrderDTO();
		
		dto.setAddress(addressConverter.toDTO(entity.getAddress()));
		dto.setDate(entity.getDate());
		dto.setDeliveryFee(entity.getDeliveryFee());
		dto.setExpectedTime(entity.getExpectedTime());
		dto.setId(entity.getId());
		dto.setNote(entity.getNote());
		dto.setPayment(paymentConverter.toDTO(entity.getPayment()));
		dto.setStatus(entity.getStatus());
		dto.setUserId(entity.getUser().getId());
		dto.setUsername(entity.getUser().getUsername());
		
		return dto;
	}

	@Override
	public List<OrderDTO> toDTOList(List<OrderEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

}
