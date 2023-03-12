package com.nhom14.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.ProductInfoDTO;
import com.nhom14.service.ProductInfoService;

@RestController
public class ProductInfoAPI {

	@Autowired
	private ProductInfoService productInfoService;

	@GetMapping("/public/api/product-info/{productCode}")
	@CrossOriginsList
	public ResponseEntity<ProductInfoDTO> getProductInfo(@PathVariable("productCode") String productCode) {
		ProductInfoDTO dto = productInfoService.findOneByProductCode(productCode);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new ProductInfoDTO());
	}

	@PutMapping("/api/product-info")
	@CrossOriginsList
	public ResponseEntity<ProductInfoDTO> putProductInfo(@RequestBody ProductInfoDTO productInfoDTO) {
		ProductInfoDTO dto = productInfoService.save(productInfoDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new ProductInfoDTO());
	}

}
