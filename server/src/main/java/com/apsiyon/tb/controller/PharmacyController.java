package com.apsiyon.tb.controller;

import com.apsiyon.tb.entities.PharmacyOnDutyResponse;
import com.apsiyon.tb.services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping("/api/pharmacies")
    public PharmacyOnDutyResponse getPharmacies(@RequestParam(value = "ilce") String ilce) {
        return pharmacyService.getPharmaciesOnDuty(ilce);
    }
}