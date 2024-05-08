package com.springms.PaymentService.repository;

import com.springms.PaymentService.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

    Payment findByOrderId(long orderId);
}
