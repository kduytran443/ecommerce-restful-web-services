package com.nhom14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	ProductEntity findOneByCode(String code);
	
}
