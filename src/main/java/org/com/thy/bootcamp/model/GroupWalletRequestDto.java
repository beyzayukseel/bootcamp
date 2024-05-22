package org.com.thy.bootcamp.model;

import java.time.LocalDate;

public record GroupWalletRequestDto(
        Long accountId,
        Long userId,
        Long name,
        LocalDate created
) {
}
