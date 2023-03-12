package com.nhom14.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.DiscountDTO;
import com.nhom14.service.DiscountService;

@RestController
public class DiscountAPI {

	@Autowired
	private DiscountService discountService;

	@GetMapping("/public/api/discount")
	@CrossOriginsList
	public ResponseEntity<List<DiscountDTO>> getDiscounts(
			@RequestParam(name = "active", defaultValue = "0", required = false) int active) {
		List<DiscountDTO> dtos = new ArrayList<>();

		if (active == 0) {
			dtos = discountService.findAll();
		} else {
			dtos = discountService.findAllActive();
		}

		return ResponseEntity.status(200).body(dtos);
	}

	@PostMapping("/api/discount")
	@CrossOriginsList
	public ResponseEntity<DiscountDTO> postDiscount(@RequestBody DiscountDTO discountDTO) {
		discountDTO.setId(null);

		DiscountDTO dto = discountService.save(discountDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(500).body(new DiscountDTO());
	}

	@PutMapping("/api/discount")
	@CrossOriginsList
	public ResponseEntity<DiscountDTO> putDiscount(@RequestBody DiscountDTO discountDTO) {
		DiscountDTO dto = discountService.save(discountDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(500).body(new DiscountDTO());
	}
	
	@DeleteMapping("/api/discount")
	@CrossOriginsList
	public ResponseEntity<DiscountDTO> deleteDiscount(@RequestBody DiscountDTO discountDTO) {
		discountService.delete(discountDTO);
		return ResponseEntity.status(200).body(new DiscountDTO());
	}
	
}
