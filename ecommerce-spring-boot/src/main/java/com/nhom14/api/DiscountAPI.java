package com.nhom14.api;

import java.util.ArrayList;
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
	public ResponseEntity<List<DiscountDTO>> getDiscounts() {
		List<DiscountDTO> dtos = discountService.findAll();
		
		if(dtos != null) {
			return ResponseEntity.status(200).body(dtos);			
		}

		return ResponseEntity.status(500).body(Collections.emptyList());
	}
	@GetMapping("/public/api/discount/product/{productCode}")
	@CrossOriginsList
	public ResponseEntity<List<DiscountDTO>> getDiscountsByProductCode(@PathVariable("productCode") String productCode){
		List<DiscountDTO> dtos = discountService.findOneByProductCode(productCode);
		if(dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(500).body(Collections.emptyList());
	}
	
	@GetMapping("/api/discount/{id}")
	@CrossOriginsList
	public ResponseEntity<DiscountDTO> getDiscountById(@PathVariable("id") Long id){
		DiscountDTO dto = discountService.findOneById(id);
		if(dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new DiscountDTO());
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
	
	@PostMapping("/api/discount-product/{productCode}")
	@CrossOriginsList
	public ResponseEntity<DiscountDTO> postDiscountProduct(@RequestBody DiscountDTO discountDTO, @PathVariable("productCode") String productCode) {
		System.out.println("productCode "+productCode);
		DiscountDTO dto = discountService.apply(discountDTO, productCode);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new DiscountDTO());
	}
	
	@DeleteMapping("/api/discount-product/{productCode}")
	@CrossOriginsList
	public ResponseEntity<DiscountDTO> deleteDiscountProduct(@RequestBody DiscountDTO discountDTO, @PathVariable("productCode") String productCode) {
		DiscountDTO dto = discountService.remove(discountDTO, productCode);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new DiscountDTO());
	}
	
}
