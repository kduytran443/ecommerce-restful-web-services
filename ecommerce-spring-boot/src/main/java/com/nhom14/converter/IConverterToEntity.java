package com.nhom14.converter;

import java.util.List;

public interface IConverterToEntity<Entity, DTO> {
	
	public Entity toEntity(DTO dto);
	public List<Entity> toEntityList(List<DTO> dtos);
	
}
