package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.OrderDetailsEntity;
import com.nhom14.entity.OrderEntity;
import com.nhom14.entity.ProductEntity;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long> {
	
	OrderDetailsEntity findOneByOrderAndProduct(OrderEntity order, ProductEntity product); 
	List<OrderDetailsEntity> findAllByOrder(OrderEntity order);
	List<OrderDetailsEntity> findAllByProduct(ProductEntity product);
	
}
