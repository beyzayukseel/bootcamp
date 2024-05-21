package org.com.thy.bootcamp.util;

public class GeneralNumberGenerator {

    public static String randomNumber() {
        Long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        String accountNumber =number.toString();
        return accountNumber;
    }
}
