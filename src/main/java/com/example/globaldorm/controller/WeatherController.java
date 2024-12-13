package com.example.globaldorm.controller;

import com.example.globaldorm.model.Weather;
import com.example.globaldorm.model.WeatherData;
import com.example.globaldorm.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String getWeather(@RequestParam String lon,
                             @RequestParam String lat,
                             @RequestParam(defaultValue = "en") String lang,
                             @RequestParam(defaultValue = "metric") String unit,
                             @RequestParam(defaultValue = "json") String output) {

        // Fetch the weather data from the service (this will check the DB first)
        Weather weather = weatherService.getWeatherData(lon, lat, lang, unit, output);

        if (weather == null) {
            return "Error retrieving weather data. Please try again later.";
        }

        // Get the current date
        LocalDate today = LocalDate.now();
        String todayStr = today.format(DateTimeFormatter.BASIC_ISO_DATE);

        // Get the list of weather data (dataseries)
        List<WeatherData> dataSeries = weather.getDataseries();

        // Find today's weather data
        Optional<WeatherData> todaysWeather = dataSeries.stream()
                .filter(data -> String.valueOf(data.getDate()).equals(todayStr))
                .findFirst();

        if (todaysWeather.isPresent()) {
            WeatherData weatherData = todaysWeather.get();
            String formattedOutput = String.format(
                    "Date: %s\nWeather: %s\nMax Temperature: %d\nMin Temperature: %d\nMax Wind Speed: %d",
                    today, weatherData.getWeather(), weatherData.getTemp2m().getMax(),
                    weatherData.getTemp2m().getMin(), weatherData.getWind10m_max());

            return formattedOutput; // Return today's weather data
        } else {
            return "No weather data available for today.";
        }
    }
}