package org.com.thy.bootcamp.repository;

import org.com.thy.bootcamp.entity.GroupWalletInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupWalletInvitationRepository extends JpaRepository<org.com.thy.bootcamp.entity.GroupWalletInvitation, Long> {
    @Query
    Optional<GroupWalletInvitation> findByLinkUrl(String link);
}
