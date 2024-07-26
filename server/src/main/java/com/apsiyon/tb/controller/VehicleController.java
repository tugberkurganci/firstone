package com.apsiyon.tb.controller;
import com.apsiyon.tb.dto.VehicleRequest;
import com.apsiyon.tb.dto.VehicleResponse;
import com.apsiyon.tb.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public VehicleResponse addVehicle(@RequestBody VehicleRequest vehicleRequest) {
        return vehicleService.addVehicle(vehicleRequest);
    }
    @GetMapping("/user/{userId}")
    public List<VehicleResponse> getVehiclesByUserId(@PathVariable Long userId) {
        return vehicleService.getVehiclesByUserId(userId);
    }
    @GetMapping
    public List<VehicleResponse> getVehicles() {
        return vehicleService.getVehiclesBy();
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }

}