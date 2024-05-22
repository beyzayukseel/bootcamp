package org.com.thy.bootcamp.model;

import java.security.Timestamp;

public record ExchangeDto (
        String rates,
        String base,
        Boolean success,
        Timestamp timestamp
) {}
