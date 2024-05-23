package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.model.PaymentDetails;
import org.com.thy.bootcamp.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public void createPayment() {

    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentDetails>> getAllPaymentsOfGroupWallet(@RequestParam Long groupWalletID) {
        return ResponseEntity.ok().body(paymentService.getAllPaymentsOfGroupWallet(groupWalletID));
    }
}
