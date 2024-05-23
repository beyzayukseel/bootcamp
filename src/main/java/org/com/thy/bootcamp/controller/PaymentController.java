package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public void createPayment() {

    }
}
