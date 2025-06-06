package com.example.globaldorm.service;

import com.example.globaldorm.model.GeoCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoCodeService {

    @Cacheable(value = "geocode", key = "#postcode")
    public GeoCode getGeocodeData(String postcode) {
        System.out.println("Fetching geocode from API for: " + postcode);

        String url = "http://api.getthedata.com/postcode/" + postcode;
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            // line (22-27) modified from https://www.digitalocean.com/community/tutorials/jackson-json-java-parser-api-example-tutorial (Pankaj, 2022)
            GeoCode geocode = objectMapper.readTree(response)
                    .path("data")
                    .traverse(objectMapper)
                    .readValueAs(GeoCode.class);
            return geocode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}