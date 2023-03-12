package com.nhom14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom14.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
