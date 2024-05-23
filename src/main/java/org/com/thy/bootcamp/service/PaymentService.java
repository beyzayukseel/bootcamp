package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
}
