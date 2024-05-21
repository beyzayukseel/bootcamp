package org.com.thy.bootcamp.util;

public record SystemResponse(
        String message,
        Integer statusCode,
        boolean isSuccess

) {}
