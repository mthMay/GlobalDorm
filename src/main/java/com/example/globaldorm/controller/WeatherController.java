package com.example.globaldorm.controller;

import com.example.globaldorm.model.Temp2M;
import com.example.globaldorm.model.Weather;
import com.example.globaldorm.model.WeatherData;
import com.example.globaldorm.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String lon,
                              @RequestParam String lat,
                              @RequestParam(defaultValue = "en") String lang,
                              @RequestParam(defaultValue = "metric") String unit,
                              @RequestParam(defaultValue = "json") String output) {
        Weather weather = weatherService.getWeatherData(lon, lat, lang, unit, output);

        if (weather == null) {
            return "Error retrieving weather data.";
        }

        List<WeatherData> dataSeries = weather.getDataseries();
        if (dataSeries != null && !dataSeries.isEmpty()) {
            WeatherData latestWeatherData = dataSeries.get(dataSeries.size() - 1);

            long dateInt = latestWeatherData.getDate();
            String weatherCondition = latestWeatherData.getWeather();
            Temp2M temp2m = latestWeatherData.getTemp2m();
            long maxTemp = temp2m.getMax();
            long minTemp = temp2m.getMin();
            long maxWind = latestWeatherData.getWind10m_max();

            String dateStr = String.valueOf(dateInt);
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String formattedOutput = String.format(
                    "Date: %s\nWeather: %s\nMax Temperature: %d\nMin Temperature: %d\nMax Wind Speed: %d",
                    formattedDate, weatherCondition, maxTemp, minTemp, maxWind);

            return formattedOutput;
        } else {
            return "No weather data available.";
        }
    }
}
