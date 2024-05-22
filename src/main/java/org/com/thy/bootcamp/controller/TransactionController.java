package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.model.CreateTransactionRequestDto;
import org.com.thy.bootcamp.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public void createTransaction(@RequestBody CreateTransactionRequestDto createTransactionRequestDto) {
        transactionService.createTransaction(createTransactionRequestDto);
    }

}
