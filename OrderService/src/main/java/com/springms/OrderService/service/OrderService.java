package com.springms.OrderService.service;

import com.springms.OrderService.model.OrderRequest;
import com.springms.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
