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
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.CategoryDTO;
import com.nhom14.service.CategoryService;

@RestController
public class CategoryAPI {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/public/api/category")
	@CrossOriginsList
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> dtos = categoryService.findAll();

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(200).body(Collections.emptyList());
	}

	@GetMapping("/public/api/category/{categoryCode}")
	@CrossOriginsList
	public ResponseEntity<?> getCategory(@PathVariable("categoryCode") String categoryCode) {
		CategoryDTO dto = categoryService.findOneByCode(categoryCode);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).build();
	}

	@PostMapping("/api/category")
	@CrossOriginsList
	public ResponseEntity<?> postCategory(CategoryDTO categoryDTO) {
		categoryDTO.setId(null);
		CategoryDTO dto = categoryService.save(categoryDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).build();
	}

	@PutMapping("/api/category")
	@CrossOriginsList
	public ResponseEntity<?> putCategory(CategoryDTO categoryDTO) {
		CategoryDTO dto = categoryService.save(categoryDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).build();
	}

	@DeleteMapping("/api/category")
	@CrossOriginsList
	public ResponseEntity<?> deleteCategory(CategoryDTO categoryDTO) {
		categoryService.delete(categoryDTO);
		return ResponseEntity.status(200).build();
	}

}
