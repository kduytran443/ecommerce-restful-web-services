package com.nhom14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.ProductInfoEntity;

public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, Long> {
	
}
