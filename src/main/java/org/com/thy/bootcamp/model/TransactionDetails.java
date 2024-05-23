package org.com.thy.bootcamp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDetails (
        String username,

        String lastname,
        LocalDate date,
        String transactionType,
        BigDecimal amount

) {}
