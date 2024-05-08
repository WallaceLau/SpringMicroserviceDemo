package com.springms.PaymentService.service;

import com.springms.PaymentService.entity.Payment;
import com.springms.PaymentService.model.PaymemtRequest;

public interface PaymentService {
    Long doPayment(PaymemtRequest paymemtRequest);

    Payment getPaymentDetailsByOrderId(long orderId);
}
