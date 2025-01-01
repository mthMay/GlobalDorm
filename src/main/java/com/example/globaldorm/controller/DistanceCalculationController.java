package com.example.globaldorm.controller;

import com.example.globaldorm.model.DistanceResponse;
import com.example.globaldorm.model.Location;
import com.example.globaldorm.service.DistanceCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/distance")
public class DistanceCalculationController {
    @Autowired
    private DistanceCalculationService distanceCalculationService;

    @GetMapping("/")
    public ResponseEntity<?> getDistance(
            @RequestParam("originLat") double originLat,
            @RequestParam("originLon") double originLon,
            @RequestParam("destLat") double destLat,
            @RequestParam("destLon") double destLon) {

        Location origin = new Location(originLat, originLon);
        Location destination = new Location(destLat, destLon);
        DistanceResponse response = distanceCalculationService.calculateDistance(origin, destination);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Cannot retrieve geocode data.");
        }

        return ResponseEntity.ok(Map.of(
                "distance", response.getDistance(),
                "duration", response.getDuration()
        ));
    }
}

