package com.nhom14.api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.OrderDTO;
import com.nhom14.entity.CustomUserDetails;
import com.nhom14.service.OrderService;

@RestController
public class OrderAPI {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/api/order")
	@CrossOriginsList
	public ResponseEntity<List<OrderDTO>> getOrders() {
		List<OrderDTO> dtos = orderService.findAllByOrderByIdDesc();
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@GetMapping("/api/order/{orderId}")
	@CrossOriginsList
	public ResponseEntity<OrderDTO> getOrder(@PathVariable("orderId") Long orderId) {
		OrderDTO dto = orderService.findOneById(orderId);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new OrderDTO());
	}
	
	@PostMapping("/api/order")
	@CrossOriginsList
	public ResponseEntity<OrderDTO> postOrder(@RequestBody OrderDTO orderDTO) {
		orderDTO.setId(null);
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		orderDTO.setUserId(userId);
		
		OrderDTO dto = orderService.save(orderDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new OrderDTO());
	}
	
	@PutMapping("/api/order")
	@CrossOriginsList
	public ResponseEntity<OrderDTO> putOrder(@RequestBody OrderDTO orderDTO) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		orderDTO.setUserId(userId);
		
		OrderDTO dto = orderService.save(orderDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new OrderDTO());
	}
	
	@DeleteMapping("/api/order")
	@CrossOriginsList
	public ResponseEntity<OrderDTO> deleteOrder(@RequestBody OrderDTO orderDTO) {
		orderService.delete(orderDTO);
		return ResponseEntity.status(200).body(new OrderDTO());
	}
	
}
