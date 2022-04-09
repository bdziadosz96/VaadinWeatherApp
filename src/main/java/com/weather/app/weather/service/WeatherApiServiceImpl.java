package com.weather.app.weather.service;

import com.weather.app.weather.domain.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
class WeatherApiServiceImpl implements WeatherApiService {
    private final RestTemplate restTemplate;
    @Value("${api.weather.key}")
    private String apiKey;

    public Weather findWeatherForCity(String city) {
        return restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey,
                Weather.class
        );
    }
}
