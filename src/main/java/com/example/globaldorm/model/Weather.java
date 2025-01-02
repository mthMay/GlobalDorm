package com.example.globaldorm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection="weather")
public class Weather {
    @Id
    private String id;
    private String product;
    private String init;
    private String lat;
    private String lon;
    private List<WeatherData> dataseries;

    public Weather() {}

    public Weather(String id, String product, String init, String lat, String lon, List<WeatherData> dataseries) {
        this.id = id;
        this.product = product;
        this.init = init;
        this.lat = lat;
        this.lon = lon;
        this.dataseries = dataseries;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public String getInit() {
        return init;
    }
    public void setInit(String init) {
        this.init = init;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public List<WeatherData> getDataseries() {
        return dataseries;
    }
    public void setDataseries(List<WeatherData> dataseries) {
        this.dataseries = dataseries;
    }
}
