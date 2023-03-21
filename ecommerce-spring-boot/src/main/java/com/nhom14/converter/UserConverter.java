package com.nhom14.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nhom14.dto.UserDTO;
import com.nhom14.entity.UserEntity;

@Component
public class UserConverter implements IConverterToDTO<UserEntity, UserDTO>, IConverterToEntity<UserEntity, UserDTO> {

	@Override
	public UserEntity toEntity(UserDTO dto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setBrithYear(dto.getBrithYear());
		userEntity.setFullname(dto.getFullname());
		userEntity.setGender(dto.getGender());
		userEntity.setId(dto.getId());
		userEntity.setPhoneNumber(dto.getPhoneNumber());
		userEntity.setUsername(dto.getUsername());
		userEntity.setAvatar(dto.getAvatar());

		userEntity.setRoles(null);

		return userEntity;
	}

	@Override
	public UserDTO toDTO(UserEntity entity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setBrithYear(entity.getBrithYear());
		userDTO.setFullname(entity.getFullname());
		userDTO.setGender(entity.getGender());
		userDTO.setId(entity.getId());
		userDTO.setPhoneNumber(entity.getPhoneNumber());
		userDTO.setUsername(entity.getUsername());
		userDTO.setAvatar(entity.getAvatar());
		userDTO.setStatus(entity.getStatus());
		if(entity.getRoles() != null) userDTO.setRole(entity.getRoles().get(0).getCode());
		return userDTO;
	}

	@Override
	public List<UserEntity> toEntityList(List<UserDTO> dtos) {
		return null;
	}

	@Override
	public List<UserDTO> toDTOList(List<UserEntity> entities) {
		return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

}
