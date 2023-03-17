package com.nhom14.api;

import java.util.ArrayList;
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
import com.nhom14.dto.ManufacturerDTO;
import com.nhom14.service.ManufacturerService;

@RestController
public class ManufacturerAPI {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@GetMapping("/public/api/manufacturer")
	@CrossOriginsList
	public ResponseEntity<List<ManufacturerDTO>> getManufacturers() {
		List<ManufacturerDTO> dtos = new ArrayList<>();

		dtos = manufacturerService.findAll();

		return ResponseEntity.status(200).body(dtos);
	}
	
	@GetMapping("/public/api/manufacturer/{productCode}")
	@CrossOriginsList
	public ResponseEntity<ManufacturerDTO> getManufacturerByProductCode(@PathVariable(name = "productCode") String productCode) {
		ManufacturerDTO dto = manufacturerService.findOneByProductCode(productCode);
		
		if(dto != null) {
			return ResponseEntity.status(200).body(dto);			
		}
		
		return ResponseEntity.status(500).body(new ManufacturerDTO());
	}
	
	@GetMapping("/public/api/manufacturer/code/{code}")
	@CrossOriginsList
	public ResponseEntity<ManufacturerDTO> getManufacturerByCode(@PathVariable(name = "code") String code) {
		ManufacturerDTO dto = manufacturerService.findOneByCode(code);
		
		if(dto != null) {
			return ResponseEntity.status(200).body(dto);			
		}
		
		return ResponseEntity.status(500).body(new ManufacturerDTO());
	}
	
	@PostMapping("/api/manufacturer")
	@CrossOriginsList
	public ResponseEntity<ManufacturerDTO> postManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
		manufacturerDTO.setId(null);
		
		ManufacturerDTO dto = manufacturerService.save(manufacturerDTO);
		if(dto != null) {
			return ResponseEntity.status(200).body(dto);		
		}
		
		return ResponseEntity.status(200).body(new ManufacturerDTO());
	}
	
	@PutMapping("/api/manufacturer")
	@CrossOriginsList
	public ResponseEntity<ManufacturerDTO> putManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
		ManufacturerDTO dto = manufacturerService.save(manufacturerDTO);
		if(dto != null) {
			return ResponseEntity.status(200).body(dto);		
		}
		
		return ResponseEntity.status(200).body(new ManufacturerDTO());
	}
	
	@DeleteMapping("/api/manufacturer")
	@CrossOriginsList
	public ResponseEntity<ManufacturerDTO> deleteManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
		manufacturerService.delete(manufacturerDTO);
		return ResponseEntity.status(200).body(new ManufacturerDTO());
	}
	
}
