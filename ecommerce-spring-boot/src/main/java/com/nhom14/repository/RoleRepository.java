package com.nhom14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
	public RoleEntity findOneByCode(String code);
	
}
