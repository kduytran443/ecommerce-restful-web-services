package com.nhom14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.PaymentTypeEntity;

public interface PaymentTypeRepository extends JpaRepository<PaymentTypeEntity, Long> {

}
