package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.AppUser;
import com.apsiyon.tb.entities.Concierge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}