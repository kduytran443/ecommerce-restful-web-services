package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.ProductSpecificationDTO;
import com.nhom14.entity.ProductSpecificationEntity;

@Component
public class ProductSpecificationConverter
		implements IConverterToEntity<ProductSpecificationEntity, ProductSpecificationDTO>,
		IConverterToDTO<ProductSpecificationEntity, ProductSpecificationDTO> {

	@Override
	public ProductSpecificationDTO toDTO(ProductSpecificationEntity entity) {
		ProductSpecificationDTO dto = new ProductSpecificationDTO();
		
		dto.setContent(entity.getContent());
		dto.setProductCode(entity.getProduct().getCode());
		dto.setSpecificationId(entity.getSpecification().getId());
		dto.setSpecificationName(entity.getSpecification().getName());
		dto.setProductId(entity.getProduct().getId());
		
		return dto;
	}

	@Override
	public List<ProductSpecificationDTO> toDTOList(List<ProductSpecificationEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public ProductSpecificationEntity toEntity(ProductSpecificationDTO dto) {
		ProductSpecificationEntity entity = new ProductSpecificationEntity();
		entity.setContent(dto.getContent());
		return entity;
	}
	public ProductSpecificationEntity toEntity(ProductSpecificationDTO dto, ProductSpecificationEntity entity) {
		entity.setContent(dto.getContent());
		return entity;
	}

	@Override
	public List<ProductSpecificationEntity> toEntityList(List<ProductSpecificationDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

}
