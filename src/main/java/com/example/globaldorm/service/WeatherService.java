package com.example.globaldorm.service;

import com.example.globaldorm.model.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();

    public Weather getWeatherData(String lon, String lat, String lang, String unit, String output) {
        String baseUrl = "https://www.7timer.info/bin/civillight.php";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("lang", lang)
                .queryParam("unit", unit)
                .queryParam("output", output)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        return gson.fromJson(response, Weather.class);
    }
}
