package org.com.thy.bootcamp.repository;

import org.com.thy.bootcamp.entity.MilPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilPointRepository extends JpaRepository<MilPoint, Long> {
}
