package com.nhom14.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.PaymentDTO;
import com.nhom14.service.PaymentService;

@RestController
public class PaymentAPI {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/api/payment")
	@CrossOriginsList
	public ResponseEntity<PaymentDTO> getPaymentByOrderId(@RequestBody PaymentDTO paymentDTO) {
		PaymentDTO dto = paymentService.findOneByOrderId(paymentDTO.getOrderId());

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new PaymentDTO());
	}

	@PostMapping("/api/payment")
	@CrossOriginsList
	public ResponseEntity<PaymentDTO> postPayment(@RequestBody PaymentDTO paymentDTO) {
		paymentDTO.setId(null);
		
		PaymentDTO dto = paymentService.save(paymentDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new PaymentDTO());
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
