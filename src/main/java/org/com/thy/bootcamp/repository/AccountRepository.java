package org.com.thy.bootcamp.repository;

import org.com.thy.bootcamp.entity.Account;
import org.com.thy.bootcamp.entity.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("Select a from Account a where a.user.id = 1 and a.id = 2")
    Optional<Account> getAccountsByUserIdAndAccountId(Long userId, Long accountId);

    List<Account> getAccountsByUserId(Long userId);

    @Query("SELECT m  from Account m where m.accountStatus  = ?1 ")
    List<Account> getAccountsByAccountStatus(AccountStatus accountStatus);

    boolean existsByIban(String iban);

    boolean existsById(Long accountId);

    Boolean existsByUserId(Long userId);

    Account findByIban(String iban);
}
