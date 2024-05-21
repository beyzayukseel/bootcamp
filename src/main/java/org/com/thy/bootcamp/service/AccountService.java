package org.com.thy.bootcamp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.thy.bootcamp.entity.*;
import org.com.thy.bootcamp.exception.ValidationOperationException;
import org.com.thy.bootcamp.model.AccountRequestDto;
import org.com.thy.bootcamp.repository.AccountRepository;
import org.com.thy.bootcamp.repository.CardRepository;
import org.com.thy.bootcamp.repository.UserRepository;
import org.com.thy.bootcamp.util.GeneralNumberGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    @Transactional
    public void createAccount(AccountRequestDto accountRequestDto) {

        User user = userRepository.findById(accountRequestDto.userId()).orElseThrow (() -> new
                ValidationOperationException.IdNotValidator("Customer ids are not match, please contact with " +
                        "customer experience team!"));

        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setAccountStatus(AccountStatus.PENDING);
        account.setBalance(account.getBalance());
        account.setCreatedDate(LocalDate.now());
        account.setBankCode(GeneralNumberGenerator.randomNumber());
        account.setCurrency(accountRequestDto.currency());
        account.setIsDeleted(Boolean.FALSE);
        account.setIban("TR" + GeneralNumberGenerator.randomNumber());
        account.setUser(user);

        Boolean checkCustomerHasAccount = accountRepository.existsByUserId(accountRequestDto.userId());

        accountRepository.save(account);

        if (Boolean.FALSE.equals(checkCustomerHasAccount)) { // if customer demand for first account, define a card default
                Card card = new Card();
                card.setCardStatus(CardStatus.PENDING);
                card.setIsDeleted(Boolean.FALSE);
                card.setBoundary(BigDecimal.ZERO);
                card.setCardNumber(GeneralNumberGenerator.randomNumber());
                card.setDebt(BigDecimal.ZERO);
                account.setCard(card);
            cardRepository.save(card);
        }
        accountRepository.save(account);
    }


}
