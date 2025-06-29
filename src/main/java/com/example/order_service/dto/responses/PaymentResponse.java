package com.example.order_service.dto.responses;

import lombok.Data;

@Data
public class PaymentResponse {
    private boolean success;
    private String transactionId;
}