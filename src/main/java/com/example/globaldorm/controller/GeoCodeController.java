package com.example.globaldorm.controller;

import com.example.globaldorm.model.GeoCode;
import com.example.globaldorm.service.GeoCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/geocode")
public class GeoCodeController {

    private GeoCodeService geocodeService;

    public GeoCodeController(GeoCodeService geocodeService) {
        this.geocodeService = geocodeService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getGeocode(@RequestParam String postcode) {
        GeoCode geocode = geocodeService.getGeocodeData(postcode);

        if (geocode == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Cannot retrieve geocode data.");
        }
        return ResponseEntity.ok(Map.of(
                "postcode", geocode.getPostcode(),
                "latitude", geocode.getLatitude(),
                "longitude", geocode.getLongitude()
        ));
    }
}

