package com.weather.app.weather.service;

import com.weather.app.weather.domain.Weather;

public interface WeatherApiService {
    Weather findWeatherForCity(String city, Boolean inMetric);
}
