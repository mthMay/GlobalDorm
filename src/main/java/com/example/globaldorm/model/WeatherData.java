package com.example.globaldorm.model;

import lombok.Data;

@Data
public class WeatherData {
    private long date;
    private String weather;
    private Temp2M temp2m;
    private long wind10m_max;

    public WeatherData() {}

    public WeatherData(long date, String weather, Temp2M temp2m, long wind10m_max) {
        this.date = date;
        this.weather = weather;
        this.temp2m = temp2m;
        this.wind10m_max = wind10m_max;
    }

    public long getDate() {
        return date;
    }
    public void setDate(long date) {
        this.date = date;
    }
    public String getWeather() {
        return weather;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }
    public Temp2M getTemp2m() {
        return temp2m;
    }
    public void setTemp2m(Temp2M temp2m) {
        this.temp2m = temp2m;
    }
    public long getWind10m_max() {
        return wind10m_max;
    }
    public void setWind10m_max(long wind10m_max) {
        this.wind10m_max = wind10m_max;
    }

}
