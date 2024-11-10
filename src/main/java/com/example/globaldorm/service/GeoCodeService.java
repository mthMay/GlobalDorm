package com.example.globaldorm.service;

import com.example.globaldorm.model.GeoCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoCodeService {

    public GeoCode getGeocodeData(String postcode) {
        String url = "http://api.getthedata.com/postcode/" + postcode;
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
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