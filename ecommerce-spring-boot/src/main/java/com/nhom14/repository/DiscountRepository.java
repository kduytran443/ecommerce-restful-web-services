package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.DiscountEntity;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
	
	List<DiscountEntity> findAllByOrderByIdAsc();
	
}
