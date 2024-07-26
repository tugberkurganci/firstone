package com.apsiyon.tb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Concierge extends BaseEntity{
    private String serviceName;
    private String description;
    private BigDecimal price;
    private boolean subscriptionBased;
    private Integer processTimeInHours;



    @ManyToMany(mappedBy = "concierges")
    @JsonIgnore
    private List<Employee> employees;
}
