package org.com.thy.bootcamp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AccountRequestDto(
        String accountNumber,
        BigDecimal balance,
        LocalDate createdDate,
        LocalDate modifiedDate,
        String currency,
        Long userId,
        Boolean isDeleted
) { }
