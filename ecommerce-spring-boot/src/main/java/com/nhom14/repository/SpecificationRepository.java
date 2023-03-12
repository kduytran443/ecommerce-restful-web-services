package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.SpecificationEntity;

public interface SpecificationRepository extends JpaRepository<SpecificationEntity,Long> {
	
	List<SpecificationEntity> findAllByCategoryId(Long categoryId);
	
}
