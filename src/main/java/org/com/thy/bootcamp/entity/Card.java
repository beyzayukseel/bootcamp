package org.com.thy.bootcamp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "CARD")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private BigDecimal boundary=BigDecimal.ZERO;
    private BigDecimal debt = BigDecimal.ZERO;
    private Boolean isDeleted;
    private LocalDate createDate;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @OneToOne
    private Account accountList;

}