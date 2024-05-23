package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.model.CreateTransactionRequestDto;
import org.com.thy.bootcamp.model.TransactionDetails;
import org.com.thy.bootcamp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public void createTransaction(@RequestBody CreateTransactionRequestDto createTransactionRequestDto) {
        transactionService.createTransaction(createTransactionRequestDto);
    }

    @GetMapping("/all")
    public List<TransactionDetails> getAllTransactionsOfGroupWallet(@RequestParam Long groupWalletID) {
        return transactionService.getAllTransactionsOfGroupWallet(groupWalletID);
    }

}
