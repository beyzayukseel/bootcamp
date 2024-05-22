package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.LinkGenerator;
import org.com.thy.bootcamp.entity.User;
import org.com.thy.bootcamp.entity.Wallet;
import org.com.thy.bootcamp.repository.LinkGeneratorRepository;
import org.com.thy.bootcamp.repository.UserRepository;
import org.com.thy.bootcamp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LinkGeneratorService {
    private final LinkGeneratorRepository linkGeneratorRepository;


    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    public LinkGenerator createLink(LinkGenerator linkGenerator) {

        User groupLeader = userRepository.findById(linkGenerator.getGroupLeader().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid group leader ID"));
        Wallet wallet = walletRepository.findById(linkGenerator.getWallet().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid wallet ID"));


        linkGenerator.setGroupLeader(groupLeader);
        linkGenerator.setWallet(wallet);

        linkGenerator.setCreated(LocalDate.now());
        linkGenerator.setLinkUrl(generateUniqueLink());
        return linkGeneratorRepository.save(linkGenerator);
    }

    private String generateUniqueLink() {
        return "http://example.com/" + UUID.randomUUID().toString();
    }

    public LinkGenerator getLink(Long id) {
        return linkGeneratorRepository.findById(id).orElse(null);
    }

    public void deleteLink(Long id) {
        linkGeneratorRepository.deleteById(id);
    }
}
