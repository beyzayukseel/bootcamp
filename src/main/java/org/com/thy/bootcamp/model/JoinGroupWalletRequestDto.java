package org.com.thy.bootcamp.model;

public record JoinGroupWalletRequestDto (
        String link,
        Long userId,
        Long accountId
) {}
