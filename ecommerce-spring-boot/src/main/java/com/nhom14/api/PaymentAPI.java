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
import com.nhom14.dto.PaymentDTO;
import com.nhom14.entity.CustomUserDetails;
import com.nhom14.service.PaymentService;

@RestController
public class PaymentAPI {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/api/payment/{orderId}")
	@CrossOriginsList
	public ResponseEntity<PaymentDTO> getPaymentByOrderId(@PathVariable("orderId") Long orderId) {
		PaymentDTO dto = paymentService.findOneByOrderId(orderId);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new PaymentDTO());
	}
	
	@GetMapping("/api/payment")
	@CrossOriginsList
	public ResponseEntity<List<PaymentDTO>> getPaymentByUserId() {
		Long userId = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUser().getId();
		List<PaymentDTO> dtos = paymentService.findAllByUserId(userId);
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(200).body(Collections.emptyList());
	}

	@PostMapping("/api/payment")
	@CrossOriginsList
	public ResponseEntity<PaymentDTO> postPayment(@RequestBody PaymentDTO paymentDTO) {
		paymentDTO.setId(null);
		
		PaymentDTO dto = paymentService.save(paymentDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(500).body(new PaymentDTO());
	}

	@PutMapping("/api/payment")
	@CrossOriginsList
	public ResponseEntity<PaymentDTO> putPayment(@RequestBody PaymentDTO paymentDTO) {
		PaymentDTO dto = paymentService.save(paymentDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new PaymentDTO());
	}

	@DeleteMapping("/api/payment")
	@CrossOriginsList
	public ResponseEntity<PaymentDTO> deletePayment(@RequestBody PaymentDTO paymentDTO) {
		paymentService.delete(paymentDTO);

		return ResponseEntity.status(200).body(new PaymentDTO());
	}

}
