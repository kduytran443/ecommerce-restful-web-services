package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhom14.dto.ProductDTO;
import com.nhom14.entity.ProductEntity;

@Component
public class ProductConverter
		implements IConverterToEntity<ProductEntity, ProductDTO>, IConverterToDTO<ProductEntity, ProductDTO> {

	@Autowired
	private DiscountConverter discountConverter;

	@Autowired
	private ManufacturerConverter manufacturerConverter;

	@Override
	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();

		entity.setAvatar(dto.getAvatar());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		if (dto.getStatus() != null)
			entity.setStatus(dto.getStatus());
		entity.setWarrantyMonth(dto.getWarrantyMonth());
		entity.setYear(dto.getYear());

		return entity;
	}

	@Override
	public List<ProductEntity> toEntityList(List<ProductDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

	@Override
	public ProductDTO toDTO(ProductEntity entity) {
		ProductDTO dto = new ProductDTO();

		dto.setAvatar(entity.getAvatar());
		if (entity.getCategory() != null) {
			dto.setCategoryCode(entity.getCategory().getCode());
			dto.setCategoryName(entity.getCategory().getName());
		}
		dto.setId(entity.getId());
		if (entity.getDiscounts() != null) {
			dto.setDiscounts(discountConverter.toDTOList(entity.getDiscounts()));
		}

		if (entity.getManufacturer() != null) {
			dto.setManufacturer(manufacturerConverter.toDTO(entity.getManufacturer()));
		}

		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setStatus(entity.getStatus());
		dto.setWarrantyMonth(entity.getWarrantyMonth());
		dto.setYear(entity.getYear());
		return dto;
	}

	@Override
	public List<ProductDTO> toDTOList(List<ProductEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

}
