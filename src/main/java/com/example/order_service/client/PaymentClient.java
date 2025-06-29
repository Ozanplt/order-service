package com.example.order_service.client;

import com.example.order_service.config.FeignConfig;
import com.example.order_service.dto.requests.PaymentRequest;
import com.example.order_service.dto.responses.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "payment-service",
        configuration = FeignConfig.class
)
public interface PaymentClient {

    @PostMapping("/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}