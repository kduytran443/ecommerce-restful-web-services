package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhom14.dto.CategoryDTO;
import com.nhom14.entity.CategoryEntity;

@Component
public class CategoryConverter
		implements IConverterToDTO<CategoryEntity, CategoryDTO>, IConverterToEntity<CategoryEntity, CategoryDTO> {


	@Override
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity entity = new CategoryEntity();

		if(dto.getCode() != null) entity.setCode(dto.getCode());
		if(dto.getDescription() != null) entity.setDescription(dto.getDescription());
		if(dto.getIcon() != null) entity.setIcon(dto.getIcon());
		if (dto.getId() != null)
			entity.setId(dto.getId());
		if(dto.getImage() != null) entity.setImage(dto.getImage());
		if(dto.getName() != null) entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
		
		return entity;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto, CategoryEntity entity) {

		if(dto.getCode() != null) entity.setCode(dto.getCode());
		if(dto.getDescription() != null) entity.setDescription(dto.getDescription());
		if(dto.getIcon() != null) entity.setIcon(dto.getIcon());
		if (dto.getId() != null)
			entity.setId(dto.getId());
		if(dto.getImage() != null) entity.setImage(dto.getImage());
		if(dto.getName() != null) entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
		
		return entity;
	}

	@Override
	public List<CategoryEntity> toEntityList(List<CategoryDTO> dtos) {
		return dtos.stream().map(item -> toEntity(item)).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO toDTO(CategoryEntity entity) {
		CategoryDTO dto = new CategoryDTO();

		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());
		dto.setIcon(entity.getIcon());
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());

		return dto;
	}

	@Override
	public List<CategoryDTO> toDTOList(List<CategoryEntity> entities) {
		return entities.stream().map(item -> toDTO(item)).collect(Collectors.toList());
	}

}
