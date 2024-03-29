package com.nhom14.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public UserEntity findOneByUsername(String username);
	public UserEntity findOneByPhoneNumber(Integer phoneNumber);
	
}
