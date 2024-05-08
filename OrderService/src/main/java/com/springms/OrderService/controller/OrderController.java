package com.springms.OrderService.controller;

import com.springms.OrderService.external.client.PaymentService;
import com.springms.OrderService.external.client.ProductService;
import com.springms.OrderService.model.OrderRequest;
import com.springms.OrderService.model.OrderResponse;
import com.springms.OrderService.model.PaymemtRequest;
import com.springms.OrderService.model.PaymentMode;
import com.springms.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    List<String> mem = new ArrayList<>();

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {

        for(int i=0;i<10000;i++){
            this.mem.add(this.getSaltString());
        }

        // productService.reduceQuantoty(orderRequest.getProductId(), orderRequest.getQuantity());
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order Id: {}", orderId);

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){
        OrderResponse orderResponse = orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }


}
