package org.com.thy.bootcamp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.thy.bootcamp.entity.*;
import org.com.thy.bootcamp.exception.ServiceOperationException;
import org.com.thy.bootcamp.exception.ValidationOperationException;
import org.com.thy.bootcamp.model.AccountRequestDto;
import org.com.thy.bootcamp.repository.AccountRepository;
import org.com.thy.bootcamp.repository.CardRepository;
import org.com.thy.bootcamp.repository.UserRepository;
import org.com.thy.bootcamp.util.GeneralNumberGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final CardService cardService;

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

        accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.NotFoundException("Account not found"));
        AccountStatus accountStatus = account.getAccountStatus();
        if (!(accountStatus == AccountStatus.APPROVED)) {
            throw new ServiceOperationException.NotValidException("Account is not valid for use");
        }
        log.info("Account ID -> {} date: {} getting", account.getId(), new Date());

        return account;

    }

    public List<Account> getAccountsByCustomerId(Long userId) {
        return new ArrayList<>(accountRepository.getAccountsByUserId(userId));
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public Boolean existAccountById(Long accountId) {
        return accountRepository.existsById(accountId);
    }

    public Boolean exitsByIban(String iban){
        return accountRepository.existsByIban(iban);
    }

    public Account findByIban(String iban){
        return accountRepository.findByIban(iban);
    }

    public Account createCardForAccount(Long accountId, Card card) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            card.setAccountList(account);
            card.setCardNumber(cardService.generateUniqueCardNumber());
            Card savedCard = cardRepository.save(card);
            account.setCard(savedCard);
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

}
