package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {

}