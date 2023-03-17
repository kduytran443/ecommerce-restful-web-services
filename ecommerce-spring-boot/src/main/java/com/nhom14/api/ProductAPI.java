package com.nhom14.api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.nhom14.dto.PageableDTO;
import com.nhom14.dto.ProductDTO;
import com.nhom14.service.ProductService;

@RestController
public class ProductAPI {

	@Autowired
	private ProductService productService;

	@GetMapping("/public/api/product")
	@CrossOriginsList
	public ResponseEntity<PageableDTO<ProductDTO>> getProductsByCategoryCode(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "8") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(name = "categoryCode", required = false) String categoryCode,
			@RequestParam(name = "manufacturerCode", required = false) String manufacturerCode) {

		List<ProductDTO> dtos = null;
		Pageable paging = new PageRequest(pageNo, pageSize);
		PageableDTO<ProductDTO> pageableDTO = new PageableDTO<>();
		
		if(categoryCode != null) {
			dtos = productService.findAllByCategoryCode(categoryCode, paging);
			pageableDTO.setData(dtos);
			pageableDTO.setPageable(paging);
			pageableDTO.setCountAll(productService.countByCategoryCode(categoryCode));
		}
		else {
			dtos = productService.findAllByManufacturerCode(manufacturerCode, paging);
			pageableDTO = new PageableDTO<>();
			pageableDTO.setData(dtos);
			pageableDTO.setPageable(paging);
			pageableDTO.setCountAll(productService.countByManufacturerCode(manufacturerCode));
		}

		if (dtos != null) {
			return ResponseEntity.status(200).body(pageableDTO);
		}

		return ResponseEntity.status(200).body(new PageableDTO<>());
	}

	@GetMapping("/public/api/product/search/{name}")
	@CrossOriginsList
	public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable("name") String name) {

		List<ProductDTO> dtos = productService.searchByName(name);

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}

		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@GetMapping("/public/api/product/details")
	@CrossOriginsList
	public ResponseEntity<List<ProductDTO>> getProductsDetails() {

		List<ProductDTO> dtos = productService.getAllProductDetails();

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}

		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@GetMapping("/public/api/product/details/{name}")
	@CrossOriginsList
	public ResponseEntity<List<ProductDTO>> getProductsDetailsByName(@PathVariable("name") String name) {

		List<ProductDTO> dtos = productService.getAllProductDetailsByName(name);

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}

		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@GetMapping("/public/api/product/{productCode}")
	@CrossOriginsList
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("productCode") String productCode) {
		ProductDTO dto = productService.findOneByCode(productCode);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new ProductDTO());
	}
	
	@GetMapping("/public/api/product/all")
	@CrossOriginsList
	public ResponseEntity<List<ProductDTO>> getAllProduct() {
		List<ProductDTO> dtos = productService.findAll();
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(500).body(Collections.emptyList());
	}
	
	@PostMapping("/api/product")
	@CrossOriginsList
	public ResponseEntity<ProductDTO> postProduct(@RequestBody ProductDTO productDTO) {
		productDTO.setId(null);
		ProductDTO dto = productService.save(productDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new ProductDTO());
	}
	
	@PutMapping("/api/product")
	@CrossOriginsList
	public ResponseEntity<ProductDTO> putProduct(@RequestBody ProductDTO productDTO) {
		ProductDTO dto = productService.save(productDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new ProductDTO());
	}
	
	@DeleteMapping("/api/product")
	@CrossOriginsList
	public ResponseEntity<ProductDTO> deleteProduct(@RequestBody ProductDTO productDTO) {
		productService.delete(productDTO);
		return ResponseEntity.status(200).body(new ProductDTO());
	}
}
