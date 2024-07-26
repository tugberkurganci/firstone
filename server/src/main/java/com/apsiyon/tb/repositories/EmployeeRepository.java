package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.Employee;
import com.apsiyon.tb.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}