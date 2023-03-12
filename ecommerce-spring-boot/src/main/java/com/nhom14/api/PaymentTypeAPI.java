package com.nhom14.api;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.PaymentTypeDTO;
import com.nhom14.service.PaymentTypeService;

@RestController
public class PaymentTypeAPI {
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@GetMapping("/public/api/payment-type")
	@CrossOriginsList
	public ResponseEntity<List<PaymentTypeDTO>> getPaymentTypes() {
		List<PaymentTypeDTO> dtos = paymentTypeService.findAll();
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}
		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
}
