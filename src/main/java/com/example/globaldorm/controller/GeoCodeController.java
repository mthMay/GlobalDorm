package com.example.globaldorm.controller;

import com.example.globaldorm.model.GeoCode;
import com.example.globaldorm.service.GeoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GeoCodeController {
    @Autowired
    private GeoCodeService geocodeService;

    @GetMapping("/geocode")
    public String getGeocode(@RequestParam String postcode) {
        GeoCode geocode = geocodeService.getGeocodeData(postcode);

        if (geocode == null) {
            return "Error retrieving geocode data.";
        }
        String formattedOutput = String.format("Postcode: %s\nLatitude: %f\nLongitude: %f",
                geocode.getPostcode(), geocode.getLatitude(), geocode.getLongitude());

        return formattedOutput;
    }
}

