package com.nhom14.api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.ProductImageDTO;
import com.nhom14.service.ProductImageService;

@RestController
public class ProductImageAPI {

	@Autowired
	private ProductImageService productImageService;

	@GetMapping("/public/api/product-image/{productCode}")
	@CrossOriginsList
	public ResponseEntity<List<ProductImageDTO>> getProductImages(@PathVariable("productCode") String productCode) {
		List<ProductImageDTO> dtos = productImageService.findAllByProductCode(productCode);

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}

		return ResponseEntity.status(200).body(Collections.emptyList());
	}

	@PostMapping("/api/product-image")
	@CrossOriginsList
	public ResponseEntity<ProductImageDTO> postProductImage(@RequestBody ProductImageDTO productImageDTO) {
		ProductImageDTO dto = productImageService.save(productImageDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new ProductImageDTO());
	}

	@DeleteMapping("/api/product-image")
	@CrossOriginsList
	public ResponseEntity<ProductImageDTO> deleteProductImage(@RequestBody ProductImageDTO productImageDTO) {
		productImageService.delete(productImageDTO);

		return ResponseEntity.status(200).body(new ProductImageDTO());
	}

}
