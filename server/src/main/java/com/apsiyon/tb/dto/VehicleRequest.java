package com.apsiyon.tb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    private Long userId;
    private String vehicleMake;
    private String vehicleModel;
    private String licensePlate;
    private LocalDateTime registrationTime;
}