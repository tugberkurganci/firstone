package com.apsiyon.tb.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.apsiyon.tb.services.ConciergeService;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee  extends AppUser {



    private String name;



    @ManyToMany(mappedBy = "employees")
    private List<Request> requests;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<WorkSchedule> workSchedules;

    @ManyToMany
    @JoinTable(name = "employee_concierge_service",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Concierge> concierges;
}
