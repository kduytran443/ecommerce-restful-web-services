package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhom14.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	ProductEntity findOneByCode(String code);
	
	@Query(name = "SELECT * FROM product p WHERE lower(p.name) LIKE lower(%:name%)", nativeQuery = true)
	List<ProductEntity> getAllByName(String name);
	
	List<ProductEntity> findByNameContainingIgnoreCase(String name);
	List<ProductEntity> findByOrderByIdDesc();
	
}
