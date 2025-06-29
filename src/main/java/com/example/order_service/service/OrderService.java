package com.example.order_service.service;

import com.example.order_service.client.PaymentClient;
import com.example.order_service.dto.requests.CreateOrderRequest;
import com.example.order_service.dto.responses.OrderResponse;
import com.example.order_service.dto.requests.PaymentRequest;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderStatus;
import com.example.order_service.exception.ResourceNotFoundException;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repo;

    @Autowired
    private PaymentClient paymentClient;

    @Transactional
    public OrderResponse create(CreateOrderRequest dto) {
        Order order = new Order();
        order.setProductId(dto.getProductId());
        order.setUserId(dto.getUserId());
        order.setAmount(dto.getAmount());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(Instant.now());
        order = repo.save(order);

        try {
            PaymentRequest paymentRequest = new PaymentRequest(order.getId(), order.getAmount());
            var paymentResponse = paymentClient.processPayment(paymentRequest);
            order.setStatus(paymentResponse.isSuccess() ? OrderStatus.PAID : OrderStatus.FAILED);
        } catch (Exception e) {
            order.setStatus(OrderStatus.FAILED);
        }
        repo.save(order);

        return new OrderResponse(order.getId(), order.getStatus(), order.getCreatedAt());
    }

    public OrderResponse getById(Long id) {
        Order order = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        return new OrderResponse(order.getId(), order.getStatus(), order.getCreatedAt());
    }
}