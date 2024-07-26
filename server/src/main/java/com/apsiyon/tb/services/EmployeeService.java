package com.apsiyon.tb.services;

import com.apsiyon.tb.dto.EmployeeRequest;
import com.apsiyon.tb.dto.EmployeeResponse;
import com.apsiyon.tb.entities.AppUser;
import com.apsiyon.tb.entities.Concierge;
import com.apsiyon.tb.entities.Employee;
import com.apsiyon.tb.entities.Role;
import com.apsiyon.tb.repositories.AppUserRepository;
import com.apsiyon.tb.repositories.ConciergeRepository;
import com.apsiyon.tb.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ConciergeRepository conciergeRepository;
    private final AppUserRepository    userRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, ConciergeRepository conciergeRepository, AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.conciergeRepository = conciergeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public Optional<EmployeeResponse> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::convertToResponse);
    }

    public EmployeeResponse addEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());



        employee.setRole(Role.EMPLOYEE);
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Concierge> concierges = conciergeRepository.findAllById(request.getConciergeIds());
        employee.setConcierges(concierges);

        Employee savedEmployee = employeeRepository.save(employee);
        return convertToResponse(savedEmployee);
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        Employee employee = employeeRepository.findById(id).get();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());

        List<Concierge> concierges = conciergeRepository.findAllById(request.getConciergeIds());
        employee.setConcierges(concierges);

        Employee updatedEmployee = employeeRepository.save(employee);
        return convertToResponse(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    private EmployeeResponse convertToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setConciergeIds(employee.getConcierges().stream().map(Concierge::getId).collect(Collectors.toList()));
        return response;
    }
}
