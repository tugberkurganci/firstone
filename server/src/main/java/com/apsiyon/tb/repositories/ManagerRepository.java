package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.Manager;
import com.apsiyon.tb.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}