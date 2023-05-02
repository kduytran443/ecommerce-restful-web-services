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
import com.nhom14.dto.ReviewDTO;
import com.nhom14.entity.CustomUserDetails;
import com.nhom14.service.ReviewService;

@RestController
public class ReviewAPI {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/public/api/review/{productCode}")
	@CrossOriginsList
	public ResponseEntity<List<ReviewDTO>> getReviewsByProductCode(@PathVariable("productCode") String productCode) {
		List<ReviewDTO> dtos = reviewService.findAllByProductCode(productCode);
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@GetMapping("/public/api/review/{productCode}/user")
	@CrossOriginsList
	public ResponseEntity<ReviewDTO> getReviewsByProductCodeAndUser(@PathVariable("productCode") String productCode) {

		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		ReviewDTO dto = reviewService.findOneByProductCodeAndUserId(productCode, userId);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new ReviewDTO());
	}

	@PostMapping("/api/review")
	@CrossOriginsList
	public ResponseEntity<ReviewDTO> postReview(@RequestBody ReviewDTO reviewDTO) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		reviewDTO.setUserId(userId);
		System.out.println("dto"+reviewDTO);
		
		ReviewDTO dto = reviewService.createReview(reviewDTO);
		
		
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).body(new ReviewDTO());
	}

	@PutMapping("/api/review")
	@CrossOriginsList
	public ResponseEntity<ReviewDTO> putReview(@RequestBody ReviewDTO reviewDTO) {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		reviewDTO.setUserId(userId);
		
		ReviewDTO dto = reviewService.editReview(reviewDTO);
		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}
		return ResponseEntity.status(500).build();
	}

	@DeleteMapping("/api/review")
	@CrossOriginsList
	public ResponseEntity<ReviewDTO> deleteReview(@RequestBody ReviewDTO reviewDTO) {
		reviewService.delete(reviewDTO);
		return ResponseEntity.status(200).body(new ReviewDTO());
	}
	
}
