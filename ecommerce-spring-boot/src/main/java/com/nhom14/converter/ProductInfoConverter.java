package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.ProductInfoDTO;
import com.nhom14.entity.ProductInfoEntity;

@Component
public class ProductInfoConverter implements IConverterToDTO<ProductInfoEntity, ProductInfoDTO>, IConverterToEntity<ProductInfoEntity, ProductInfoDTO> {

	@Override
	public ProductInfoDTO toDTO(ProductInfoEntity entity) {
		ProductInfoDTO dto = new ProductInfoDTO();
		
		dto.setContent(entity.getContent());
		dto.setId(entity.getId());
		dto.setProductCode(entity.getProduct().getCode());
		dto.setProductId(entity.getProduct().getId());
		
		return dto;
	}

	@Override
	public List<ProductInfoDTO> toDTOList(List<ProductInfoEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public ProductInfoEntity toEntity(ProductInfoDTO dto) {
		ProductInfoEntity entity = new ProductInfoEntity();
		entity.setContent(dto.getContent());
		entity.setId(dto.getId());
		return entity;
	}
	public ProductInfoEntity toEntity(ProductInfoDTO dto, ProductInfoEntity entity) {
		entity.setContent(dto.getContent());
		entity.setId(dto.getId());
		return entity;
	}

	@Override
	public List<ProductInfoEntity> toEntityList(List<ProductInfoDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

}
