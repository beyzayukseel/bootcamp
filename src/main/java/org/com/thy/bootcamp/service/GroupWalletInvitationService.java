package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.thy.bootcamp.entity.Account;
import org.com.thy.bootcamp.entity.GroupWalletInvitation;
import org.com.thy.bootcamp.entity.User;
import org.com.thy.bootcamp.entity.Wallet;
import org.com.thy.bootcamp.exception.ValidationOperationException;
import org.com.thy.bootcamp.model.JoinGroupWalletRequestDto;
import org.com.thy.bootcamp.model.NotificationModel;
import org.com.thy.bootcamp.model.SendInvitationLinkRequest;
import org.com.thy.bootcamp.repository.AccountRepository;
import org.com.thy.bootcamp.repository.GroupWalletInvitationRepository;
import org.com.thy.bootcamp.repository.UserRepository;
import org.com.thy.bootcamp.repository.WalletRepository;
import org.com.thy.bootcamp.service.notification.SmsSender;
import org.com.thy.bootcamp.util.SystemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupWalletInvitationService {

    private final GroupWalletInvitationRepository groupWalletInvitationRepository;

    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    private final AccountRepository accountRepository;

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
        return "http://generatedLink/" + UUID.randomUUID();
    }

    public GroupWalletInvitation getLink(Long id) {
        return groupWalletInvitationRepository.findById(id).orElse(null);
    }

    public void deleteLink(Long id) {
        groupWalletInvitationRepository.deleteById(id);
    }

    public ResponseEntity<SystemResponse> joinGroupWalletWithInvitationLink(JoinGroupWalletRequestDto requestDto) {

        userRepository.findById(requestDto.userId()).orElseThrow(() -> new ValidationOperationException.IdNotValidator("User Not Found!"));

        GroupWalletInvitation groupWalletInvitation = groupWalletInvitationRepository.findByLinkUrl(requestDto.link()).orElseThrow(() ->
                new ValidationOperationException.NotValidException("The link is not valid!"));

        Account account = accountRepository.findById(requestDto.accountId()).orElseThrow(() ->
                new ValidationOperationException.IdNotValidator("The account you are trying to enter in Group wallet could not be found!"));

        Wallet wallet = walletRepository.findById(groupWalletInvitation.getWallet().getId()).orElseThrow(
                () -> new ValidationOperationException.NotValidException("Something happened! The wallet you are trying to enter could not be found!"));

        wallet.setAccountList(List.of(account));
        account.setWallet(wallet);
        try {
            walletRepository.save(wallet);
            accountRepository.save(account);
        } catch (Exception e) {
            log.error("Something happened in DB!", e, LocalDate.now());
        }

        return ResponseEntity.ok().body(new SystemResponse("Entered Group Wallet successfully!", 200, true));

    }
}
