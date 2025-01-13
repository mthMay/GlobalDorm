package com.example.globaldorm.service;

import com.example.globaldorm.model.DistanceResponse;
import com.example.globaldorm.model.GeoCode;
import com.example.globaldorm.model.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceCalculationService {

    private static final String API_KEY = "5b3ce3597851110001cf6248dfe94eb32e9442b7acf7a0762c11471a";
    private static final String BASE_URL = "https://api.openrouteservice.org/v2/directions/foot-walking";

    private final GeoCodeService geoCodeService;
    public DistanceCalculationService(GeoCodeService geoCodeService) {
        this.geoCodeService = geoCodeService;
    }
    @Cacheable(value = "distance", key="#originPostcode + '_' + #destinationPostcode")
    public DistanceResponse calculateDistance(String originPostcode, String destinationPostcode) {
        System.out.println("Fetching for distance " + originPostcode + " to " + destinationPostcode);

        GeoCode originGeocode = geoCodeService.getGeocodeData(originPostcode);
        GeoCode destinationGeocode = geoCodeService.getGeocodeData(destinationPostcode);

        Location origin = new Location(originGeocode.getLatitude(), originGeocode.getLongitude());
        Location destination = new Location(destinationGeocode.getLatitude(), destinationGeocode.getLongitude());

        String url = BASE_URL + "?api_key=" + API_KEY + "&start=" + origin.getLongitude() + "," + origin.getLatitude() + "&end=" + destination.getLongitude() + "," + destination.getLatitude();
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            // line (38-44) modified from https://www.digitalocean.com/community/tutorials/jackson-json-java-parser-api-example-tutorial (Pankaj, 2022)
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

