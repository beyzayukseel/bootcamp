package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.Payment;
import org.com.thy.bootcamp.entity.Wallet;
import org.com.thy.bootcamp.exception.ValidationOperationException;
import org.com.thy.bootcamp.model.PaymentDetails;
import org.com.thy.bootcamp.model.TransactionDetails;
import org.com.thy.bootcamp.repository.PaymentRepository;
import org.com.thy.bootcamp.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final WalletRepository walletRepository;

    public List<PaymentDetails> getAllPaymentsOfGroupWallet(Long groupWalletID) {
        Wallet wallet = walletRepository.findById(groupWalletID).orElseThrow(() ->
                new ValidationOperationException.IdNotValidator("Wallet couldn't find!"));

        List<Payment> allPaymentsToWallet = wallet.getPaymentList();

        return allPaymentsToWallet.stream().map(it ->
                new PaymentDetails(wallet.getLeader().getName(), wallet.getLeader().getLastname(),
                        it.getCreated(), it.getAmount(), it.getReceiver().getCompanyName())).toList();

    }
}
