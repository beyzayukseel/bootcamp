package org.com.thy.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupWalletInvitationRepository extends JpaRepository<org.com.thy.bootcamp.entity.GroupWalletInvitation, Long> {
}
