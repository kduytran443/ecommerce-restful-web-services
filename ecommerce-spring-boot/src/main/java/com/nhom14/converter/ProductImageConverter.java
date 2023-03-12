package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.ProductImageDTO;
import com.nhom14.entity.ProductImageEntity;

@Component
public class ProductImageConverter implements IConverterToDTO<ProductImageEntity, ProductImageDTO>, IConverterToEntity<ProductImageEntity, ProductImageDTO> {

	@Override
	public ProductImageDTO toDTO(ProductImageEntity entity) {
		ProductImageDTO dto = new ProductImageDTO();
		dto.setData(entity.getData());
		dto.setId(entity.getId());
		if(entity.getProduct() != null) dto.setProductCode(entity.getProduct().getCode());
		return dto;
	}

	@Override
	public List<ProductImageDTO> toDTOList(List<ProductImageEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public ProductImageEntity toEntity(ProductImageDTO dto) {
		ProductImageEntity entity = new ProductImageEntity();
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		return entity;
	}

	@Override
	public List<ProductImageEntity> toEntityList(List<ProductImageDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

}
