package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.User;
import com.apsiyon.tb.entities.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {
}