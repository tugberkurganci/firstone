package com.apsiyon.tb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String vehicleMake;
    private String vehicleModel;
    private String licensePlate;
    private LocalDateTime registrationTime;
}