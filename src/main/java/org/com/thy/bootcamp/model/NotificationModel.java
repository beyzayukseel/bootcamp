package org.com.thy.bootcamp.model;

public record NotificationModel (
        String generateLink,
        String message,
        String phoneNumber,
        String firstName,
        String lastName
) {
}
