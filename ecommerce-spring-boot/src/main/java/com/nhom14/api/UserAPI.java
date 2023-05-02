package com.nhom14.api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.UserDTO;
import com.nhom14.entity.CustomUserDetails;
import com.nhom14.service.UserService;

@RestController
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/user")
	@CrossOriginsList
	public ResponseEntity<UserDTO> getUser() {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		UserDTO dto = userService.findOneById(userId);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new UserDTO());
	}
	
	@GetMapping("/api/user/all")
	@CrossOriginsList
	public ResponseEntity<List<UserDTO>> getAllUser() {
		List<UserDTO> dtos = userService.findAll();
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(500).body(Collections.emptyList());
	}
	
	@PutMapping("/api/user")
	@CrossOriginsList
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		userDTO.setId(userId);
		UserDTO dto = userService.save(userDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new UserDTO());
	}

	@PutMapping("/api/user/avatar")
	@CrossOriginsList
	public ResponseEntity<?> updateUserAvatar(@RequestBody UserDTO userDTO) {
		userService.updateAvatar(userDTO.getAvatar());
		return ResponseEntity.status(200).body(new UserDTO());
	}
	
	@DeleteMapping("/api/user")
	@CrossOriginsList
	public ResponseEntity<UserDTO> deleteUser(@RequestBody UserDTO userDTO) {
		UserDTO dto = userService.delete(userDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new UserDTO());
	}
	
}
