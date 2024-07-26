package com.apsiyon.tb.controller;

import com.apsiyon.tb.dto.TaxiStandResponse;
import com.apsiyon.tb.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PlaceController {

    private final PlaceService placesService;

    @Autowired
    public PlaceController(PlaceService placesService) {
        this.placesService = placesService;
    }

    @GetMapping("/api/searchNearby")
    public ResponseEntity<TaxiStandResponse> searchNearby(
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "longitude") double longitude,
            @RequestParam(value = "radius", defaultValue = "2000") double radius) throws IOException {

            TaxiStandResponse result = placesService.searchNearby(latitude, longitude, radius);
            return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
