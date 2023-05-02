package com.nhom14.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhom14.converter.UserConverter;
import com.nhom14.dto.UserDTO;
import com.nhom14.entity.CustomUserDetails;
import com.nhom14.entity.RoleEntity;
import com.nhom14.entity.UserEntity;
import com.nhom14.repository.RoleRepository;
import com.nhom14.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) {
		// Kiểm tra xem user có tồn tại trong database không?
		UserEntity user = userRepository.findOneByUsername(username);
		if (user == null) {
			throw new RuntimeException("Tài khoản không tồn tại");
		}
		if (user.getStatus() == 0) {
			throw new RuntimeException("Tài khoản đã bị xóa");
		}
		return new CustomUserDetails(user);
	}

	public UserDetails loadUserById(Long Id) {
		// Kiểm tra xem user có tồn tại trong database không?
		UserEntity user = userRepository.findOne(Id);
		if (user == null || user.getStatus() == 0) {
			throw new RuntimeException("Tài khoản không tồn tại");
		}
		return new CustomUserDetails(user);
	}

	public UserDTO signUp(UserDTO userDTO) {

		UserEntity userCheckEntity = userRepository.findOneByPhoneNumber(userDTO.getPhoneNumber());
		if (userCheckEntity != null) {
			throw new RuntimeException("Số điện thoại đã được đăng ký");
		}
		userCheckEntity = userRepository.findOneByUsername(userDTO.getUsername());
		if (userCheckEntity != null) {
			throw new RuntimeException("Tài khoản đã tồn tại");
		}

		UserEntity userEntity = userConverter.toEntity(userDTO);

		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		userEntity.setPassword(encodedPassword);

		// Adding USER role code for register
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		roles.add(roleRepository.findOneByCode("USER"));
		userEntity.setRoles(roles);
		userEntity.setStatus(1);

		// Make sure id of new user is empty
		userEntity.setId(null);

		return userConverter.toDTO(userRepository.save(userEntity));
	}

	public UserDTO signUpAdmin(UserDTO userDTO) {

		UserEntity userCheckEntity = userRepository.findOneByPhoneNumber(userDTO.getPhoneNumber());
		if (userCheckEntity != null) {
			throw new RuntimeException("Số điện thoại đã được đăng ký");
		}
		userCheckEntity = userRepository.findOneByUsername(userDTO.getUsername());
		if (userCheckEntity != null) {
			throw new RuntimeException("Tài khoản đã tồn tại");
		}

		UserEntity userEntity = userConverter.toEntity(userDTO);
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		userEntity.setPassword(encodedPassword);

		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		roles.add(roleRepository.findOneByCode("ADMIN"));
		userEntity.setRoles(roles);

		// Make sure id of new user is empty
		userEntity.setId(null);
		userEntity.setStatus(1);

		return userConverter.toDTO(userRepository.save(userEntity));
	}

	public UserDTO findOneById(Long id) {
		return userConverter.toDTO(userRepository.findOne(id));
	}

	public UserDTO save(UserDTO dto) {
		UserEntity userEntity = userRepository.findOne(dto.getId());
		if (dto.getAvatar() != null)
			userEntity.setAvatar(dto.getAvatar());
		if (dto.getBrithYear() != null)
			userEntity.setBrithYear(dto.getBrithYear());
		userEntity.setFullname(dto.getFullname());
		userEntity.setGender(dto.getGender());
		userEntity.setPhoneNumber(dto.getPhoneNumber());
		userEntity = userRepository.save(userEntity);
		return userConverter.toDTO(userEntity);
	}

	public UserDTO delete(UserDTO dto) {
		UserEntity userEntity = userRepository.findOneByUsername(dto.getUsername());
		userEntity.setStatus(0);
		userEntity = userRepository.save(userEntity);
		return userConverter.toDTO(userEntity);
	}

	public List<UserDTO> findAll() {
		return userConverter.toDTOList(userRepository.findAll());
	}

	public void updateAvatar(String avatar) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		UserEntity userEntity = userRepository.findOne(userId);
		userEntity.setAvatar(avatar);
		userRepository.save(userEntity);
	}
	
}
