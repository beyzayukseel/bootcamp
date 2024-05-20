package org.com.thy.bootcamp.repository;

import org.com.thy.bootcamp.entity.LinkGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkGeneratorRepository extends JpaRepository<LinkGenerator, Long> {
}
