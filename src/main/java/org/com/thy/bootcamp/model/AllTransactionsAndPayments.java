package org.com.thy.bootcamp.model;

import java.util.List;

public record AllTransactionsAndPayments(
        List<PaymentDetails> allPayments,
        List<TransactionDetails> allTransactions
) {
}
