package org.com.thy.bootcamp.repository;

import org.com.thy.bootcamp.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByIbanNumber(String iban);

    List<Wallet> findWalletsByLeaderId(Long leaderId);

    Wallet findByIbanNumber(String iban);
}
