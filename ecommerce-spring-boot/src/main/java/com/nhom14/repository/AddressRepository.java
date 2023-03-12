package com.nhom14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
