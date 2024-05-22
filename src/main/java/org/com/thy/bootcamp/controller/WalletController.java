package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.model.GroupWalletRequestDto;
import org.com.thy.bootcamp.service.WalletService;
import org.com.thy.bootcamp.util.SystemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/group")
    public ResponseEntity<SystemResponse> createGroupWallet(@RequestBody GroupWalletRequestDto requestDto) {
        return walletService.createGroupWallet(requestDto);
    }
}
