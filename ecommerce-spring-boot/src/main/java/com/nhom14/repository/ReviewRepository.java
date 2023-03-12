package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.ReviewEntity;
import com.nhom14.entity.UserEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{
	List<ReviewEntity> findAllByProduct(ProductEntity product);
	List<ReviewEntity> findAllByUser(UserEntity user);
	ReviewEntity findOneByProductAndUser(ProductEntity product, UserEntity user);
}
