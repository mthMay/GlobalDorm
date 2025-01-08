package com.example.globaldorm.service;

import com.example.globaldorm.model.GeoCode;
import com.example.globaldorm.model.Weather;
import com.example.globaldorm.model.WeatherData;
import com.example.globaldorm.repository.WeatherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    private WeatherRepository weatherRepository;
    private final GeoCodeService geoCodeService;

    public WeatherService(WeatherRepository weatherRepository, GeoCodeService geoCodeService) {
        this.weatherRepository = weatherRepository;
        this.geoCodeService =  geoCodeService;
    }

    @Cacheable(value="weather",  key = "#postcode" )
    public Weather getWeatherData(String postcode, String lang, String unit, String output) {
        System.out.println("Fetching weather from API for: " + postcode);

        GeoCode geocode = geoCodeService.getGeocodeData(postcode);

        double lat = geocode.getLatitude();
        double lon = geocode.getLongitude();

        // Get today's date in yyyyMMdd format (without hour)
        LocalDate today = LocalDate.now();
        String todayStr = today.format(DateTimeFormatter.BASIC_ISO_DATE); // format as yyyyMMdd

        // Query the database using lat and lon (without the init field)
        Optional<Weather> existingWeather = weatherRepository.findByLatAndLon(Double.toString(lat), Double.toString(lon));

        if (existingWeather.isPresent()) {
            Weather weather = existingWeather.get();
            List<WeatherData> dataseries = weather.getDataseries();

            // Check if there is any weather data for today
            Optional<WeatherData> todaysWeather = dataseries.stream()
                    .filter(data -> String.valueOf(data.getDate()).equals(todayStr))
                    .findFirst();

            if (todaysWeather.isPresent()) {
                // If today's data exists, return it
                return weather;
            } else {
                weatherRepository.delete(weather);
                // If today's data is not found, fetch new data from the API
                return fetchAndSaveWeatherData(Double.toString(lon), Double.toString(lat), lang, unit, output);
            }
        } else {
            // If no weather data is found, fetch from API
            return fetchAndSaveWeatherData(Double.toString(lon), Double.toString(lat), lang, unit, output);
        }
    }

    private Weather fetchAndSaveWeatherData(String lon, String lat, String lang, String unit, String output) {
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

            // Set latitude and longitude in the weather object
            weather.setLat(lat);
            weather.setLon(lon);

            // Save the fetched weather data into MongoDB
            weatherRepository.save(weather);

            return weather;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}