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
import com.nhom14.dto.SpecificationDTO;
import com.nhom14.service.SpecificationService;

@RestController
public class SpecificationAPI {
	
	@Autowired
	private SpecificationService specificationService;
	
	@GetMapping("/public/api/specification/{categoryCode}")
	@CrossOriginsList
	public ResponseEntity<List<SpecificationDTO>> getSpecifications(@PathVariable("categoryCode") String categoryCode) {
		List<SpecificationDTO> dtos = specificationService.findAllByCategoryCode(categoryCode);
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@PostMapping("/api/specification")
	@CrossOriginsList
	public ResponseEntity<SpecificationDTO> postSpecification(@RequestBody SpecificationDTO specificationDTO) {
		specificationDTO.setId(null);
		SpecificationDTO dto = specificationService.save(specificationDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new SpecificationDTO());
	}
	
	@PutMapping("/api/specification")
	@CrossOriginsList
	public ResponseEntity<SpecificationDTO> putSpecification(@RequestBody SpecificationDTO specificationDTO) {
		SpecificationDTO dto = specificationService.save(specificationDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new SpecificationDTO());
	}

	@DeleteMapping("/api/specification")
	@CrossOriginsList
	public ResponseEntity<SpecificationDTO> deleteSpecification(@RequestBody SpecificationDTO specificationDTO) {
		System.out.println("specificationDTO "+specificationDTO);
		specificationService.delete(specificationDTO);
		return ResponseEntity.ok(new SpecificationDTO());
	}
	
}
