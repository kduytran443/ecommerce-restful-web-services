package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.CartEntity;
import com.nhom14.entity.ProductEntity;
import com.nhom14.entity.UserEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

	List<CartEntity> findAllByUser(UserEntity user);

	CartEntity findOneByUserAndProduct(UserEntity user, ProductEntity product);

}
