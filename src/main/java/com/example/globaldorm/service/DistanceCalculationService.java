package com.example.globaldorm.service;

import com.example.globaldorm.model.DistanceResponse;
import com.example.globaldorm.model.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceCalculationService {

    private static final String API_KEY = "5b3ce3597851110001cf6248dfe94eb32e9442b7acf7a0762c11471a";
    private static final String BASE_URL = "https://api.openrouteservice.org/v2/directions/foot-walking";

    public class RouteNotFoundException extends RuntimeException {
        public RouteNotFoundException(String message) {
            super(message);
        }
    }

    public DistanceResponse calculateDistance(Location origin, Location destination) {
        String url = BASE_URL + "?api_key=" + API_KEY + "&start=" + origin.getLongitude() + "," + origin.getLatitude() + "&end=" + destination.getLongitude() + "," + destination.getLatitude();
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode featuresNode = rootNode.path("features");
            if (featuresNode.isArray() && featuresNode.size() > 0) {
                JsonNode firstFeature = featuresNode.get(0);
                JsonNode propertiesNode = firstFeature.path("properties");
                JsonNode summaryNode = propertiesNode.path("summary");
                double distance = summaryNode.path("distance").asDouble();
                double duration = summaryNode.path("duration").asDouble();
                return new DistanceResponse(distance, duration);
            }else {
                throw new RouteNotFoundException("No route found between the specified locations.");
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new RouteNotFoundException("No routable points found near the specified locations.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while calculating distance.");
        }
    }
}
