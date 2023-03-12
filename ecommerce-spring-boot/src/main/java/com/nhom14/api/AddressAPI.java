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
import com.nhom14.dto.AddressDTO;
import com.nhom14.service.AddressService;

@RestController
public class AddressAPI {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/api/address/{username}")
	@CrossOriginsList
	public ResponseEntity<List<AddressDTO>> getAddresses(@PathVariable("username") String username) {
		List<AddressDTO> dtos = addressService.findAllByUsername(username);
		
		if (dtos != null) {
			return ResponseEntity.status(200).body(dtos);
		}

		return ResponseEntity.status(200).body(Collections.emptyList());
	}
	
	@PostMapping("/api/address")
	@CrossOriginsList
	public ResponseEntity<AddressDTO> postProductSpecification(@RequestBody AddressDTO addressDTO) {
		addressDTO.setId(null);
		AddressDTO dto = addressService.save(addressDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new AddressDTO());
	}
	
	@PutMapping("/api/address")
	@CrossOriginsList
	public ResponseEntity<AddressDTO> putProductSpecification(@RequestBody AddressDTO addressDTO) {
		AddressDTO dto = addressService.save(addressDTO);

		if (dto != null) {
			return ResponseEntity.status(200).body(dto);
		}

		return ResponseEntity.status(200).body(new AddressDTO());
	}
	
	@DeleteMapping("/api/address")
	@CrossOriginsList
	public ResponseEntity<AddressDTO> deleteProductSpecification(@RequestBody AddressDTO addressDTO) {
		addressService.delete(addressDTO);

		return ResponseEntity.status(200).body(new AddressDTO());
	}
	
}
