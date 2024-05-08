package com.springms.PaymentService.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymemtRequest {

    private long orderId;

    private String referenceNumber;

    private long amount;

    private PaymentMode paymentMode;
}
