package com.nhom14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.ProductImageEntity;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
	
}
