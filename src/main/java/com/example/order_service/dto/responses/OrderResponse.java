package com.example.order_service.dto.responses;

import com.example.order_service.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private OrderStatus status;
    private Instant createdAt;
}