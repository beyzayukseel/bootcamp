package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.thy.bootcamp.entity.Account;
import org.com.thy.bootcamp.entity.Wallet;
import org.com.thy.bootcamp.entity.WalletType;
import org.com.thy.bootcamp.exception.ValidationOperationException;
import org.com.thy.bootcamp.model.GroupWalletRequestDto;
import org.com.thy.bootcamp.repository.AccountRepository;
import org.com.thy.bootcamp.repository.WalletRepository;
import org.com.thy.bootcamp.util.SystemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {

    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;


    public ResponseEntity<SystemResponse> createGroupWallet(GroupWalletRequestDto requestDto) {
        Account account = accountRepository.getAccountsByUserIdAndAccountId(requestDto.userId(), requestDto.accountId()).orElseThrow( () -> new
                ValidationOperationException.IdNotValidator ("Account ids are not match, please contact with " +
                "customer experience team!"));

        Wallet wallet = new Wallet();
        wallet.setCreated(requestDto.created());
        wallet.setType(WalletType.GROUP);
        wallet.setAccountList(List.of(account));

        try{
            walletRepository.save(wallet);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SystemResponse("Created Successfully", 201, true));

        } catch (Exception e) {

            log.error("Something wrong on db!", LocalDate.now());
            return ResponseEntity.internalServerError().build();
        }
    }


}
