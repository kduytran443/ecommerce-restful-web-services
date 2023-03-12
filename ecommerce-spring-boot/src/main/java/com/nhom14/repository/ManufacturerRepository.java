package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.ManufacturerEntity;

public interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, Long> {
	
	ManufacturerEntity findOneByCode(String code);
	
}
