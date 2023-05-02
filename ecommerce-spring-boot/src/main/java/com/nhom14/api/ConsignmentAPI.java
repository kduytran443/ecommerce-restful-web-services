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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.ConsignmentDTO;
import com.nhom14.service.ConsignmentService;

@RestController
public class ConsignmentAPI {

	@Autowired
	private ConsignmentService consignmentService;

	@GetMapping("/api/consignment")
	@CrossOriginsList
	public ResponseEntity<List<ConsignmentDTO>> getConsignments(
			@RequestParam(name = "productCode", required = false) String productCode) {
		List<ConsignmentDTO> dtos = null;

		if (productCode != null) {
			dtos = consignmentService.findAllByProductCode(productCode);
		} else {
			dtos = consignmentService.findAll();
		}

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}

		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@GetMapping("/api/consignment/{id}")
	@CrossOriginsList
	public ResponseEntity<ConsignmentDTO> getConsignmentById(@PathVariable("id") Long id) {
		ConsignmentDTO dto = consignmentService.findOneById(id);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(500).body(new ConsignmentDTO());
	}
	
	@GetMapping("/api/consignment/product/{productCode}")
	@CrossOriginsList
	public ResponseEntity<List<ConsignmentDTO>> getConsignmentByProductCode(@PathVariable("productCode") String productCode) {
		List<ConsignmentDTO> dtos = consignmentService.findAllByProductCode(productCode);

		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}

		return ResponseEntity.status(500).body(Collections.emptyList());
	}

	@PostMapping("/api/consignment")
	@CrossOriginsList
	public ResponseEntity<ConsignmentDTO> postConsignment(@RequestBody ConsignmentDTO consignmentDTO) {
		consignmentDTO.setId(null);
		ConsignmentDTO dto = consignmentService.save(consignmentDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new ConsignmentDTO());
	}

	@PutMapping("/api/consignment")
	@CrossOriginsList
	public ResponseEntity<ConsignmentDTO> putConsignment(@RequestBody ConsignmentDTO consignmentDTO) {
		ConsignmentDTO dto = consignmentService.save(consignmentDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new ConsignmentDTO());
	}

	@DeleteMapping("/api/consignment")
	@CrossOriginsList
	public ResponseEntity<ConsignmentDTO> deleteConsignment(@RequestBody ConsignmentDTO consignmentDTO) {
		consignmentService.delete(consignmentDTO);
		return ResponseEntity.status(200).body(new ConsignmentDTO());
	}

}
