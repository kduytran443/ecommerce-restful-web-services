package com.nhom14.api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.OrderDetailsDTO;
import com.nhom14.service.OrderDetailsService;

@RestController
public class OrderDetailsAPI {

	@Autowired
	private OrderDetailsService orderDetailsService;

	@GetMapping("/api/order-details/{productCode}")
	@CrossOriginsList
	public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails(@PathVariable("productCode") String productCode) {
		List<OrderDetailsDTO> dtos = orderDetailsService.findAllByProductCode(productCode);
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(200).body(Collections.emptyList());
	}

	@PostMapping("/api/order-details")
	@CrossOriginsList
	public ResponseEntity<OrderDetailsDTO> postOrderDetails(@RequestBody OrderDetailsDTO orderDetailsDTO) {
		OrderDetailsDTO dto = orderDetailsService.save(orderDetailsDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new OrderDetailsDTO());
	}

	@PutMapping("/api/order-details")
	@CrossOriginsList
	public ResponseEntity<OrderDetailsDTO> putOrderDetails(@RequestBody OrderDetailsDTO orderDetailsDTO) {
		OrderDetailsDTO dto = orderDetailsService.save(orderDetailsDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new OrderDetailsDTO());
	}

	@DeleteMapping("/api/order-details")
	@CrossOriginsList
	public ResponseEntity<OrderDetailsDTO> deleteOrderDetails(@RequestBody OrderDetailsDTO orderDetailsDTO) {
		orderDetailsService.delete(orderDetailsDTO);
		return ResponseEntity.status(200).body(new OrderDetailsDTO());
	}

}
