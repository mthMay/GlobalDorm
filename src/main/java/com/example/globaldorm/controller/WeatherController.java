package com.example.globaldorm.controller;

import com.example.globaldorm.model.Weather;
import com.example.globaldorm.model.WeatherData;
import com.example.globaldorm.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.185:3000"})
@RequestMapping("/api/weather")
public class WeatherController {

    private WeatherService weatherService;

    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getWeather(@RequestParam String lon,
                                     @RequestParam String lat,
                                     @RequestParam(defaultValue = "en") String lang,
                                     @RequestParam(defaultValue = "metric") String unit,
                                     @RequestParam(defaultValue = "json") String output) {

        // Fetch the weather data from the service (this will check the DB first)
        Weather weather = weatherService.getWeatherData(lon, lat, lang, unit, output);

        if (weather == null) {
            return ResponseEntity.internalServerError().body("Error retrieving weather data.");
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
            WeatherData data = todaysWeather.get();
            return ResponseEntity.ok(Map.of(
                    "date", today.toString(),
                    "maxTemperature", data.getTemp2m().getMax(),
                    "minTemperature", data.getTemp2m().getMin(),
                    "weather", data.getWeather(),
                    "maxWindSpeed", data.getWind10m_max()
            ));
        } else {
            return ResponseEntity.status(204).body("No weather data available for today.");
        }
    }
}