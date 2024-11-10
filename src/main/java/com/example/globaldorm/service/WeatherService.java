package com.example.globaldorm.service;

import com.example.globaldorm.model.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    public Weather getWeatherData(String lon, String lat, String lang, String unit, String output) {
        String baseUrl = "https://www.7timer.info/bin/civillight.php";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("lang", lang)
                .queryParam("unit", unit)
                .queryParam("output", output)
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Weather weather = objectMapper.readValue(response, Weather.class);
            return weather;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}