package org.com.thy.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String bankCode;
    private BigDecimal balance= BigDecimal.ZERO;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    private UUID iban;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modifiedDate;

    private String currency;

    private AccountStatus accountStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    private Boolean isDeleted;

}