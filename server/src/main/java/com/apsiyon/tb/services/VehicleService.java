package com.apsiyon.tb.services;

import com.apsiyon.tb.dto.VehicleRequest;
import com.apsiyon.tb.dto.VehicleResponse;
import com.apsiyon.tb.entities.User;
import com.apsiyon.tb.entities.Vehicle;
import com.apsiyon.tb.repositories.UserRepository;
import com.apsiyon.tb.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public VehicleResponse addVehicle(VehicleRequest vehicleRequest) {
        User user = userRepository.findById(vehicleRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vehicle vehicle = new Vehicle();
        vehicle.setUser(user);
        vehicle.setVehicleMake(vehicleRequest.getVehicleMake());
        vehicle.setVehicleModel(vehicleRequest.getVehicleModel());
        vehicle.setLicensePlate(vehicleRequest.getLicensePlate());
        vehicle.setRegistrationTime(LocalDateTime.now());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return mapToResponse(savedVehicle);
    }
    public List<VehicleResponse> getVehiclesByUserId(Long userId) {
        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);
        return vehicles.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private VehicleResponse mapToResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getUser().getId(),
                vehicle.getVehicleMake(),
                vehicle.getVehicleModel(),
                vehicle.getLicensePlate(),
                vehicle.getRegistrationTime()
        );
    }

    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    public List<VehicleResponse> getVehiclesBy() {
        return vehicleRepository.findAll().stream().map(this::mapToResponse).toList();
    }
}
