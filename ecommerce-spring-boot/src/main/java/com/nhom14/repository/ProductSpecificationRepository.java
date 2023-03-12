package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.ProductSpecificationEntity;
import com.nhom14.entity.SpecificationEntity;

public interface ProductSpecificationRepository extends JpaRepository<ProductSpecificationEntity, Long> {
	
	List<ProductSpecificationEntity> findAllByProduct(ProductEntity product);
	List<ProductSpecificationEntity> findAllBySpecification(SpecificationEntity specification);
	ProductSpecificationEntity findOneByProductAndSpecification(ProductEntity product, SpecificationEntity specification);
	
}
