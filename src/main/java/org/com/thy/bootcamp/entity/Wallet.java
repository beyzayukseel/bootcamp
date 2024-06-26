package org.com.thy.bootcamp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "WALLET")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WalletType type;

    private BigDecimal totalAmount;

    private String ibanNumber;

    private LocalDate created;

    private String currency;

    @OneToOne(fetch = FetchType.LAZY)
    private User leader; //leader (main user) of group wallet

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Account> accountList;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Payment> paymentList;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Transaction> transactionList;
}