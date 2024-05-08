package com.springms.OrderService.service;

import com.springms.OrderService.exception.CustomException;
import com.springms.OrderService.external.client.PaymentService;
import com.springms.OrderService.model.OrderRequest;
import com.springms.OrderService.model.OrderResponse;
import com.springms.OrderService.model.PaymemtRequest;
import com.springms.OrderService.model.PaymentMode;
import com.springms.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.springms.OrderService.entity.Order;
import org.springframework.web.client.RestTemplate;
import static com.springms.OrderService.model.OrderResponse.*;

import java.time.Instant;
import java.util.UUID;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Placing Order Request: {}", orderRequest);

        Order order = Order.builder().amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);
        log.info("Order place successfully with orderId: {}", order.getId());


        PaymemtRequest paymemtRequest = PaymemtRequest.builder()
                .orderId(order.getId())
                .amount(orderRequest.getTotalAmount())
                .referenceNumber(UUID.randomUUID().toString())
                .paymentMode(PaymentMode.CREDIT_CARD)
                .build();

        // if the exception is not caught in this block
        // then it will propagate up and be caught by the ExceptionHandler
        try{
            paymentService.doPayment(paymemtRequest);
            order.setOrderStatus("SUCCESS");
        }catch(Exception ex){
            order.setOrderStatus("FAILED");
        }

        order = orderRepository.save(order);
        return order.getId();

    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(()-> new CustomException("Order not found for the order id: "+orderId, "ORDER_NOT_FOUND", HttpStatus.NOT_FOUND.value()));

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .build();

        Payment payment = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/"+order.getId(), Payment.class);

        orderResponse.setPayment(payment);

        Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(), Product.class);

        orderResponse.setProductDetails(product);

        return orderResponse;
    }
}
