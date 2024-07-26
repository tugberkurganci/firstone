package com.apsiyon.tb.repositories;

import com.apsiyon.tb.dto.RequestResponse;
import com.apsiyon.tb.entities.Concierge;
import com.apsiyon.tb.entities.Request;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM request_employee WHERE request_id = :requestId", nativeQuery = true)
    void deleteEmployeeAssociations(Long requestId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM work_schedule WHERE request_id = :requestId", nativeQuery = true)
    void deleteWorkSchedulesByRequestId(@Param("requestId") Long requestId);

    List<Request> findByEmployeesId(Long userId);
}