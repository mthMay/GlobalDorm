package com.example.globaldorm.controller;

import com.example.globaldorm.model.DistanceResponse;
import com.example.globaldorm.model.Location;
import com.example.globaldorm.service.DistanceCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DistanceCalculationController {
    @Autowired
    private DistanceCalculationService distanceCalculationService;

    @GetMapping("/distance")
    public String getDistance(
            @RequestParam("originLat") double originLat,
            @RequestParam("originLon") double originLon,
            @RequestParam("destLat") double destLat,
            @RequestParam("destLon") double destLon) {

        Location origin = new Location(originLat, originLon);
        Location destination = new Location(destLat, destLon);
        DistanceResponse response = distanceCalculationService.calculateDistance(origin, destination);
        if (response == null) {
            return "Error retrieving distance and duration data.";
        }
        String formattedOutput = String.format("Distance: %.2f meters\nDuration: %.2f minutes",
                response.getDistance(),
                response.getDuration() / 60.0);

        return formattedOutput;
    }
}

