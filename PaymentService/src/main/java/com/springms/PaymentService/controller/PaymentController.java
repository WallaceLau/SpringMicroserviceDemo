package com.springms.PaymentService.controller;

import com.springms.PaymentService.entity.Payment;
import com.springms.PaymentService.model.PaymemtRequest;
import com.springms.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymemtRequest paymemtRequest){
        return new ResponseEntity<Long>(paymentService.doPayment(paymemtRequest), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Payment> getPaymentDetailsByOrderId(@PathVariable long orderId){
        return new ResponseEntity<>(paymentService.getPaymentDetailsByOrderId(orderId), HttpStatus.OK);
    }
}
