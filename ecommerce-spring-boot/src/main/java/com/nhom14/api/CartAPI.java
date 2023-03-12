package com.nhom14.api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.CartDTO;
import com.nhom14.entity.CustomUserDetails;
import com.nhom14.service.CartService;

@RestController
public class CartAPI {

	@Autowired
	private CartService cartService;

	@GetMapping("/api/cart")
	@CrossOriginsList
	public ResponseEntity<List<CartDTO>> getCarts() {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();

		List<CartDTO> dtos = cartService.findAllByUserId(userId);

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(200).body(Collections.emptyList());
	}

	@PostMapping("/api/cart")
	@CrossOriginsList
	public ResponseEntity<CartDTO> postCart(@RequestBody CartDTO cartDTO) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		cartDTO.setUserId(userId);

		CartDTO dto = cartService.save(cartDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new CartDTO());
	}

	@PutMapping("/api/cart")
	@CrossOriginsList
	public ResponseEntity<CartDTO> putCart(@RequestBody CartDTO cartDTO) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		cartDTO.setUserId(userId);

		CartDTO dto = cartService.save(cartDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new CartDTO());
	}

	@DeleteMapping("/api/cart")
	@CrossOriginsList
	public ResponseEntity<CartDTO> deleteCart(@RequestBody CartDTO cartDTO) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		cartDTO.setUserId(userId);

		cartService.delete(cartDTO);
		return ResponseEntity.ok(new CartDTO());
	}

}
