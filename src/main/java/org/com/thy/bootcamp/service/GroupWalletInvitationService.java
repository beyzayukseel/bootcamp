package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.GroupWalletInvitation;
import org.com.thy.bootcamp.entity.User;
import org.com.thy.bootcamp.entity.Wallet;
import org.com.thy.bootcamp.model.NotificationModel;
import org.com.thy.bootcamp.model.SendInvitationLinkRequest;
import org.com.thy.bootcamp.repository.GroupWalletInvitationRepository;
import org.com.thy.bootcamp.repository.UserRepository;
import org.com.thy.bootcamp.repository.WalletRepository;
import org.com.thy.bootcamp.service.notification.SmsSender;
import org.com.thy.bootcamp.util.SystemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupWalletInvitationService {

    private final GroupWalletInvitationRepository groupWalletInvitationRepository;

    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    private final SmsSender smsSender;

    public ResponseEntity<SystemResponse> sendGroupWalletInvitationLink(SendInvitationLinkRequest request) {

        User groupLeader = userRepository.findById(request.leaderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid group leader ID"));

        Wallet wallet = walletRepository.findById(request.walletId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid wallet ID"));

        if (!Objects.equals(wallet.getLeader().getId(), request.leaderId())) return ResponseEntity.notFound().build();

        GroupWalletInvitation linkGenerator = new GroupWalletInvitation();

        String generatedLink = generateUniqueLink();

        linkGenerator.setGroupLeader(groupLeader);
        linkGenerator.setWallet(wallet);
        linkGenerator.setCreated(LocalDate.now());
        linkGenerator.setLinkUrl(generatedLink);

        groupWalletInvitationRepository.save(linkGenerator);

        smsSender.sendNotification(new NotificationModel(generatedLink, "", request.invitedPhoneNumber(), request.invitedName(), request.invitedLastname()));

        return ResponseEntity.ok().body(new SystemResponse("Group wallet invite link sent successfully!", 200, true));

    }

    private String generateUniqueLink() {
        return "http://example.com/" + UUID.randomUUID();
    }

    public GroupWalletInvitation getLink(Long id) {
        return groupWalletInvitationRepository.findById(id).orElse(null);
    }

    public void deleteLink(Long id) {
        groupWalletInvitationRepository.deleteById(id);
    }
}
