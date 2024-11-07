package com.example.globaldorm.controller;

import com.example.globaldorm.model.Weather;
import com.example.globaldorm.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public Weather getWeather(@RequestParam String lon,
                              @RequestParam String lat,
                              @RequestParam(defaultValue = "en") String lang,
                              @RequestParam(defaultValue = "metric") String unit,
                              @RequestParam(defaultValue = "json") String output) {
        return weatherService.getWeatherData(lon, lat, lang, unit, output);
    }
}
