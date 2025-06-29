package com.example.order_service.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentRequest {
    private Long orderId;
    private BigDecimal amount;
}