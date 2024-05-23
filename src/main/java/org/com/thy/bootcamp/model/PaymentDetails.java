package org.com.thy.bootcamp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentDetails(
        String username,
        String lastname,
        LocalDate date,
        BigDecimal amount,
        String paymentReceiverName
) {
}
