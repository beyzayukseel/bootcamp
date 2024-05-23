package org.com.thy.bootcamp.service;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.thy.bootcamp.entity.Account;
import org.com.thy.bootcamp.entity.Transaction;
import org.com.thy.bootcamp.entity.Wallet;
import org.com.thy.bootcamp.exception.ServiceOperationException;
import org.com.thy.bootcamp.exception.ValidationOperationException;
import org.com.thy.bootcamp.model.CreateTransactionRequestDto;
import org.com.thy.bootcamp.model.ExchangeDto;
import org.com.thy.bootcamp.model.TransactionDetails;
import org.com.thy.bootcamp.repository.TransactionRepository;
import org.com.thy.bootcamp.repository.WalletRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final AccountService accountService;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final ExchangeModule exchangeModule;


    @Transactional(Transactional.TxType.REQUIRED)
    public void createTransaction(CreateTransactionRequestDto createTransactionDto) {

        boolean checkSourceNumber = walletRepository.existsByIbanNumber(createTransactionDto.sourceIbanNumber());
        Boolean checkDestinationNumber = accountService.exitsByIban(createTransactionDto.destinationIbanNumber());

        if (Boolean.FALSE.equals((checkSourceNumber))) {
            throw new ServiceOperationException.NotFoundException("Not Found Source Wallet Account");
        }
        if (Boolean.TRUE.equals(checkDestinationNumber)) {
            throw new ServiceOperationException.NotFoundException("Not Found Destination Iban");
        }

        Account destinationAccount = accountService.findByIban(createTransactionDto.destinationIbanNumber());

        Wallet sourceWalletAccount = walletRepository.findByIbanNumber(createTransactionDto.sourceIbanNumber());

        moneyTransfer(sourceWalletAccount, destinationAccount, createTransactionDto.quantity());

        Transaction transaction = new Transaction();
        transaction.setAccount(destinationAccount);
        transaction.setWallet(sourceWalletAccount);
        transaction.setAmount(createTransactionDto.quantity());
        transaction.setDestinationIbanNumber(createTransactionDto.destinationIbanNumber());
        transaction.setSourceIbanNumber(createTransactionDto.sourceIbanNumber());
        transaction.setCreatedDate(LocalDate.now());

        try {
            transactionRepository.save(transaction);
        } catch (Exception e) {

            log.error("Something happened in DB!", LocalDate.now());
        }
    }

    private void moneyTransfer(Wallet sourceAccount, Account destinationAccount, BigDecimal moneyTransfer) {
        BigDecimal sourceAccountBalance = sourceAccount.getTotalAmount();
        int checkMoneyQuantityIsEnough = sourceAccountBalance.compareTo(moneyTransfer);
        if (checkMoneyQuantityIsEnough == -1) {
            throw new ServiceOperationException.NotValidException("Not enough money in your account!");
        }

        String sourceAccountCurrency = sourceAccount.getCurrency();
        String destinationAccountCurrency = destinationAccount.getCurrency();

        if (sourceAccountCurrency == destinationAccountCurrency){
            sourceAccount.setTotalAmount(sourceAccountBalance.subtract(moneyTransfer));
            destinationAccount.setBalance(moneyTransfer.add(moneyTransfer));
        } else {
            ResponseEntity<ExchangeDto> exchangeModel = exchangeModule.getExchange(sourceAccount.getCurrency().toString());
            sourceAccount.setTotalAmount(sourceAccountBalance.subtract(moneyTransfer));
            BigDecimal exchangeQuantity = moneyTransfer.multiply(ExchangeModule.exchangeValueByCurrency(exchangeModel.getBody().rates()));

            destinationAccount.setBalance(destinationAccount.getBalance().add(exchangeQuantity));
            accountService.saveAccount(destinationAccount);
            walletRepository.save(sourceAccount);
        }
    }

    public List<TransactionDetails> getAllTransactionsOfGroupWallet(Long groupWalletID) {
        Wallet wallet = walletRepository.findById(groupWalletID).orElseThrow(() -> new ValidationOperationException.IdNotValidator("Wallet couldn't find"));
        List<Transaction> allTransactionsToWallet = wallet.getTransactionList();
        return allTransactionsToWallet.stream().map( it ->
                new TransactionDetails(it.getAccount().getUser().getName(), it.getAccount().getUser().getLastname(),
                        it.getCreatedDate(), it.getDestinationIbanNumber(), it.getAmount())).toList();
    }
}
