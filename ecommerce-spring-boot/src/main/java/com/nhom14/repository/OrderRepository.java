package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	
	List<OrderEntity> findAllByOrderByIdDesc();
	
}
