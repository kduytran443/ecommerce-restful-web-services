package com.nhom14.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nhom14.converter.AddressConverter;
import com.nhom14.dto.AddressDTO;
import com.nhom14.entity.AddressEntity;
import com.nhom14.entity.UserEntity;
import com.nhom14.repository.AddressRepository;
import com.nhom14.repository.UserRepository;
import com.nhom14.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressConverter addressConverter;

	@Override
	public List<AddressDTO> findAllByUserId(Long userId) {
		UserEntity userEntity = userRepository.findOne(userId);

		List<AddressEntity> addressEntities = userEntity.getAddresses().stream().filter(item -> item.getStatus() == 1)
				.collect(Collectors.toList());

		return addressConverter.toDTOList(addressEntities);
	}

	@Override
	public AddressDTO save(AddressDTO addressDTO) {
		AddressEntity addressEntity = null;

		if (addressDTO.getId() != null) {
			addressEntity = addressRepository.findOne(addressDTO.getId());
			addressEntity = addressConverter.toEntity(addressDTO, addressEntity);
		} else {
			addressEntity = addressConverter.toEntity(addressDTO);
			UserEntity userEntity = userRepository.findOne(addressDTO.getUserId());
			addressEntity.setUser(userEntity);
			addressEntity.setStatus(1);
		}

		if (addressEntity != null) {
			addressEntity = addressRepository.save(addressEntity);
			return addressConverter.toDTO(addressEntity);
		}

		return null;
	}

	@Override
	public void delete(AddressDTO addressDTO) {
		AddressEntity addressEntity = addressRepository.findOne(addressDTO.getId());
		addressEntity.setStatus(0);
		addressRepository.save(addressEntity);
	}

}
