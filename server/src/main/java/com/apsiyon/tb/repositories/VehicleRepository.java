package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.User;
import com.apsiyon.tb.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByUserId(Long userId);
}
