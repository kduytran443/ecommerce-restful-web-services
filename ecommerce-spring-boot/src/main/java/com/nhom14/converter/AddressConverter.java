package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.AddressDTO;
import com.nhom14.entity.AddressEntity;

@Component
public class AddressConverter
		implements IConverterToDTO<AddressEntity, AddressDTO>, IConverterToEntity<AddressEntity, AddressDTO> {

	@Override
	public AddressEntity toEntity(AddressDTO dto) {
		AddressEntity entity = new AddressEntity();
		entity.setDetails(dto.getDetails());
		entity.setId(dto.getId());
		return entity;
	}

	public AddressEntity toEntity(AddressDTO dto, AddressEntity entity) {
		entity.setDetails(dto.getDetails());
		entity.setId(dto.getId());
		return entity;
	}

	@Override
	public List<AddressEntity> toEntityList(List<AddressDTO> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}

	@Override
	public AddressDTO toDTO(AddressEntity entity) {
		AddressDTO dto = new AddressDTO();

		dto.setDetails(entity.getDetails());
		dto.setId(entity.getId());
		dto.setUsername(entity.getUser().getUsername());
		dto.setUserId(entity.getUser().getId());
		dto.setStatus(entity.getStatus());

		return dto;
	}

	@Override
	public List<AddressDTO> toDTOList(List<AddressEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

}
