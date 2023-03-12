package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	
	List<CategoryEntity> findAllByStatus(int status);
	
	CategoryEntity findOneByCode(String code);
	
}
