package com.nhom14.converter;

import java.util.List;

public interface IConverterToDTO<Entity, DTO> {
	
	public DTO toDTO(Entity entity);
	public List<DTO> toDTOList(List<Entity> entities);
	
}
