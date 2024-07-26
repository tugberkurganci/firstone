package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.Concierge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConciergeRepository extends JpaRepository<Concierge, Long> {
}