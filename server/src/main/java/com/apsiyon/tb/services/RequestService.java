package com.apsiyon.tb.services;

import com.apsiyon.tb.dto.RequestRequest;
import com.apsiyon.tb.dto.RequestResponse;
import com.apsiyon.tb.entities.*;
import com.apsiyon.tb.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ConciergeService conciergeService;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    private ObjectMapper objectMapper=new ObjectMapper();

    public RequestResponse createRequest(RequestRequest request) {
        // 1. Kullanıcı ve servis var mı kontrol et
        AppUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Concierge service = conciergeService.getConciergeById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        // 2. Uygun çalışanları seç
        List<Employee> availableEmployees = findAvailableEmployees(service, request.getStartTime(), request.getEndTime());
        if (availableEmployees.isEmpty()) {
            throw new RuntimeException("No available employees for the service at the specified time.");
        }

        // 3. Request'i oluştur
        Request newRequest = new Request();
        newRequest.setUser(user);
        newRequest.setService(service);
        newRequest.setStartTime(request.getStartTime());
        newRequest.setEndTime(request.getEndTime());
        newRequest.setStarted(request.isStarted());
        newRequest.setStatus(request.getStatus());

        Employee employee = availableEmployees.stream().findFirst().get();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        newRequest.setEmployees(employees);
        Request savedRequest = requestRepository.save(newRequest);

        // Çalışanlara çalışma zamanı ataması yap
        WorkSchedule workSchedule = new WorkSchedule();
        workSchedule.setStartTime(LocalTime.from(request.getStartTime()));
        workSchedule.setEndTime(LocalTime.from(request.getEndTime()));
        workSchedule.setEmployee(employee);
        workSchedule.setRequest(savedRequest); // WorkSchedule'ın hangi request'e ait olduğunu belirtiyoruz
        workScheduleRepository.save(workSchedule);

        return convertToResponse(savedRequest);
    }

    // Belirtilen hizmet için uygun çalışanları bul
    private List<Employee> findAvailableEmployees(Concierge service, LocalDateTime startTime, LocalDateTime endTime) {
        List<Employee> employees = service.getEmployees();
        List<Employee> availableEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            // Çalışanın tüm çalışma zamanlarını al
            List<WorkSchedule> workSchedules = employee.getWorkSchedules();

            // Belirtilen zaman aralığında çalışma çakışması olup olmadığını kontrol et
            boolean isAvailable = workSchedules.stream()
                    .noneMatch(ws -> ws.getStartTime().isBefore(LocalTime.from(endTime)) || ws.getEndTime().isAfter(LocalTime.from(startTime)));

            if (isAvailable) {
                availableEmployees.add(employee);
            }
        }

        return availableEmployees;
    }

    public List<RequestResponse> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        return requests.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public Optional<RequestResponse> getRequestById(Long id) {
        return requestRepository.findById(id).map(this::convertToResponse);
    }

    public RequestResponse updateRequestStatus(RequestRequest   request1) {
        Request request = requestRepository.findById(request1.getId())
                .orElseThrow(() -> new RuntimeException("Request not found: " + request1.getId()));
        request.setStatus(request1.getStatus());
        request.setStarted(request1.isStarted());
        request.setStartTime(request1.getStartTime());
        request.setEndTime(request1.getEndTime());
        Request updatedRequest = requestRepository.save(request);
        return convertToResponse(updatedRequest);
    }

    private RequestResponse convertToResponse(Request request) {
        RequestResponse response = new RequestResponse();


        response.setId(request.getId());
        response.setServiceName(request.getService().getServiceName());
        response.setStartTime(request.getStartTime());
        response.setEndTime(request.getEndTime());
        response.setStarted(request.isStarted());
        response.setStatus(request.getStatus());
        response.setEmployeeNames(request.getEmployees().stream().map(Employee::getName).collect(Collectors.toList()));
        response.setUserId(request.getUser().getId());
        response.setUsername(request.getUser().getUsername());
        return response;
    }

    public List<RequestResponse> getRequestByUserId(Long userId) {

        List<Request> requests = requestRepository.findByUserId(userId);
        return requests.stream()
                .map(this::convertToResponse) // Convert each Request to RequestResponse
                .collect(Collectors.toList());     }
    @Transactional
    public String deleteRequestByID(Long id) {
        requestRepository.deleteWorkSchedulesByRequestId(id);
        requestRepository.deleteEmployeeAssociations(id);
        requestRepository.deleteById(id);
    return "successfully deleted";

    }

    public List<RequestResponse> getRequestByEmloyeesId(Long userId) {

        return requestRepository.findByEmployeesId(userId).stream().map(this::convertToResponse).toList();
    }
}

