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
import com.nhom14.dto.ProductSpecificationDTO;
import com.nhom14.service.ProductSpecificationService;

@RestController
public class ProductSpecificationAPI {
	
	@Autowired
	private ProductSpecificationService productSpecificationService;
	
	@GetMapping("/public/api/product-specification/{productCode}")
	@CrossOriginsList
	public ResponseEntity<List<ProductSpecificationDTO>> getProductSpecification(@PathVariable("productCode") String productCode) {
		System.out.println("??? "+productCode);
		List<ProductSpecificationDTO> dtos = productSpecificationService.findAllByProductCode(productCode);

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}

		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@PostMapping("/api/product-specification")
	@CrossOriginsList
	public ResponseEntity<ProductSpecificationDTO> postProductSpecification(@RequestBody ProductSpecificationDTO productSpecificationDTO) {
		ProductSpecificationDTO dto = productSpecificationService.save(productSpecificationDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new ProductSpecificationDTO());
	}
	
	@PutMapping("/api/product-specification")
	@CrossOriginsList
	public ResponseEntity<ProductSpecificationDTO> putProductSpecification(@RequestBody ProductSpecificationDTO productSpecificationDTO) {
		ProductSpecificationDTO dto = productSpecificationService.save(productSpecificationDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new ProductSpecificationDTO());
	}
	
	@DeleteMapping("/api/product-specification")
	@CrossOriginsList
	public ResponseEntity<ProductSpecificationDTO> deleteProductSpecification(@RequestBody ProductSpecificationDTO productSpecificationDTO) {
		productSpecificationService.delete(productSpecificationDTO);
		return ResponseEntity.status(200).body(new ProductSpecificationDTO());
	}
	
}
