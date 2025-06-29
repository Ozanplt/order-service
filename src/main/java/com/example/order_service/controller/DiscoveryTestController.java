package com.example.order_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiscoveryTestController {

    private final org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;

    @GetMapping("/discovery/payment")
    public List<ServiceInstance> paymentInstances() {
        return discoveryClient.getInstances("payment-service");
    }
}