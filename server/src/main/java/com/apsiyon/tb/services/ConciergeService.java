package com.apsiyon.tb.services;

import com.apsiyon.tb.dto.ConciergeRequest;
import com.apsiyon.tb.dto.ConciergeResponse;
import com.apsiyon.tb.entities.Concierge;
import com.apsiyon.tb.entities.Employee;
import com.apsiyon.tb.repositories.ConciergeRepository;
import com.apsiyon.tb.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConciergeService {

    private final ConciergeRepository conciergeRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ConciergeService(ConciergeRepository conciergeRepository, EmployeeRepository employeeRepository) {
        this.conciergeRepository = conciergeRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<ConciergeResponse> getAllServices() {
        List<Concierge> services = conciergeRepository.findAll();
        return services.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public Optional<ConciergeResponse> getServiceById(Long id) {
        return conciergeRepository.findById(id).map(this::convertToResponse);
    }

    public BigDecimal getServicePrice(Long serviceId) {
        Concierge service = conciergeRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return service.getPrice();
    }

    public ConciergeResponse addService(ConciergeRequest request) {
        Concierge service = new Concierge();
        service.setServiceName(request.getServiceName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setSubscriptionBased(request.isSubscriptionBased());
        service.setProcessTimeInHours(request.getProcessTimeInHours());

        List<Employee> employees = employeeRepository.findAllById(request.getEmployeeIds());
        service.setEmployees(employees);

        Concierge savedService = conciergeRepository.save(service);
        return convertToResponse(savedService);
    }

    public ConciergeResponse updateService(Long id, ConciergeRequest request) {
        if (!conciergeRepository.existsById(id)) {
            throw new RuntimeException("Service not found");
        }
        Concierge service = conciergeRepository.findById(id).get();
        service.setServiceName(request.getServiceName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setSubscriptionBased(request.isSubscriptionBased());
        service.setProcessTimeInHours(request.getProcessTimeInHours());

        List<Employee> employees = employeeRepository.findAllById(request.getEmployeeIds());
        service.setEmployees(employees);

        Concierge updatedService = conciergeRepository.save(service);
        return convertToResponse(updatedService);
    }

    public void deleteService(Long id) {
        if (!conciergeRepository.existsById(id)) {
            throw new RuntimeException("Service not found");
        }
        conciergeRepository.deleteById(id);
    }

    private ConciergeResponse convertToResponse(Concierge service) {
        ConciergeResponse response = new ConciergeResponse();
        response.setId(service.getId());
        response.setServiceName(service.getServiceName());
        response.setDescription(service.getDescription());
        response.setPrice(service.getPrice());
        response.setSubscriptionBased(service.isSubscriptionBased());
        response.setProcessTimeInHours(service.getProcessTimeInHours());
        response.setEmployeeIds(service.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        return response;
    }

    public Optional<Concierge> getConciergeById(Long serviceId) {

        return conciergeRepository.findById(serviceId);
    }
}


