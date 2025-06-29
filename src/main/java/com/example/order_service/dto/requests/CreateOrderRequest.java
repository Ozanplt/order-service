package com.example.order_service.dto.requests;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {
    @NotNull
    private Long productId;

    @NotNull
    private Long userId;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;
}