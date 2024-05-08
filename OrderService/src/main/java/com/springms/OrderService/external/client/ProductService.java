package com.springms.OrderService.external.client;

import com.springms.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
@CircuitBreaker(name="external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {

    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantoty(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    );

    default void fallback(Exception ex){
        throw new CustomException("Product service is not available",
                "UNAVAILABLE",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
