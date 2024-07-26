package com.apsiyon.tb.entities;

import com.apsiyon.tb.services.ConciergeService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Request extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;


    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Concierge service;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean started;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "request_employee",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;


    @Enumerated(EnumType.STRING)
    private RequestStatus status;

}

