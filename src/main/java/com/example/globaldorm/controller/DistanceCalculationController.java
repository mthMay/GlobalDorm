package com.example.globaldorm.controller;

import com.example.globaldorm.model.DistanceResponse;
import com.example.globaldorm.model.Location;
import com.example.globaldorm.service.DistanceCalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/distance")
public class DistanceCalculationController {

    private DistanceCalculationService distanceCalculationService;

    public DistanceCalculationController(DistanceCalculationService distanceCalculationService) {
        this.distanceCalculationService = distanceCalculationService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getDistance(
            @RequestParam("originPostcode") String originPostcode,
            @RequestParam("destinationPostcode") String destinationPostcode) {

        DistanceResponse response = distanceCalculationService.calculateDistance(originPostcode, destinationPostcode);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Cannot retrieve geocode data.");
        }

        return ResponseEntity.ok(Map.of(
                "distance", response.getDistance(),
                "duration", response.getDuration()
        ));
    }
}

