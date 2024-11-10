package com.example.globaldorm.service;

import com.example.globaldorm.model.DistanceResponse;
import com.example.globaldorm.model.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceCalculationService {

    private static final String API_KEY = "5b3ce3597851110001cf6248dfe94eb32e9442b7acf7a0762c11471a";
    private static final String BASE_URL = "https://api.openrouteservice.org/v2/directions/foot-walking";

    public DistanceResponse calculateDistance(Location origin, Location destination) {
        String url = BASE_URL + "?api_key=" + API_KEY + "&start=" + origin.getLongitude() + "," + origin.getLatitude() + "&end=" + destination.getLongitude() + "," + destination.getLatitude();
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            DistanceResponse distanceResponse = objectMapper.readTree(response)
                    .path("features")
                    .get(0)
                    .path("properties")
                    .path("summary")
                    .traverse(objectMapper)
                    .readValueAs(DistanceResponse.class);
            return distanceResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

