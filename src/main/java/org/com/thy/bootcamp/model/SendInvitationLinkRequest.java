package org.com.thy.bootcamp.model;

public record SendInvitationLinkRequest (
        Long leaderId,
        Long walletId,
        String invitedPhoneNumber,
        String invitedName,
        String invitedLastname

){
}
