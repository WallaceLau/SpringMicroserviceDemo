package com.springms.PaymentService.service;

import com.springms.PaymentService.entity.Payment;
import com.springms.PaymentService.model.PaymemtRequest;
import com.springms.PaymentService.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Long doPayment(PaymemtRequest paymemtRequest) {
        log.info("Recording oayment details: {}", paymemtRequest);

        Payment payment = Payment.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymemtRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymemtRequest.getOrderId())
                .referenceNumber(paymemtRequest.getReferenceNumber())
                .amount(paymemtRequest.getAmount())
                .build();
        paymentRepository.save(payment);
        return payment.getPaymentId();
    }

    @Override
    public Payment getPaymentDetailsByOrderId(long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
