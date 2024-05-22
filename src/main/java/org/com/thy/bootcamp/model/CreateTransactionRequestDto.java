package org.com.thy.bootcamp.model;


import java.math.BigDecimal;

public record CreateTransactionRequestDto(
        String destinationIbanNumber,
        BigDecimal quantity,
        String sourceIbanNumber
) {
}
