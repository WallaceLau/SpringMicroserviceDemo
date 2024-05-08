package com.springms.OrderService.external.client;

import com.springms.OrderService.exception.CustomException;
import com.springms.OrderService.model.PaymemtRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name="external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymemtRequest paymemtRequest);

    default void fallback(Exception ex){
        throw new CustomException("Payment service is not available",
                "UNAVAILABLE",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
