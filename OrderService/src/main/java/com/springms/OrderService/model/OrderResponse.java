package com.springms.OrderService.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;

    private Instant orderDate;

    private String orderStatus;

    private long amount;

    private Product productDetails;

    private Payment payment;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Product {


        private long productId;

        private String productName;

        private long price;

        private long quantity;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Payment {

        private long paymentId;

        private long orderId;

        private String paymentMode;

        private String referenceNumber;

        private Instant paymentDate;

        private String paymentStatus;

        private long amount;

    }

}
