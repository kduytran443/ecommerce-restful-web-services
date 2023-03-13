package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhom14.dto.CartDTO;
import com.nhom14.entity.CartEntity;

@Component
public class CartConverter implements IConverterToDTO<CartEntity, CartDTO>, IConverterToEntity<CartEntity, CartDTO> {

	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public CartDTO toDTO(CartEntity entity) {
		CartDTO dto = new CartDTO();
		
		dto.setProductCode(entity.getProduct().getCode());
		dto.setProductId(entity.getProduct().getId());
		dto.setProduct(productConverter.toDTO(entity.getProduct()));
		dto.setQuantity(entity.getQuantity());
		dto.setUserId(entity.getUser().getId());
		dto.setUsername(entity.getUser().getUsername());
		
		return dto;
	}

	@Override
	public List<CartDTO> toDTOList(List<CartEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public CartEntity toEntity(CartDTO dto) {
		CartEntity entity = new CartEntity();
		entity.setQuantity(dto.getQuantity());
		return entity;
	}

	public CartEntity toEntity(CartDTO dto, CartEntity entity) {
		entity.setQuantity(dto.getQuantity());
		return entity;
	}

	@Override
	public List<CartEntity> toEntityList(List<CartDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

}
