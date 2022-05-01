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

    public Weather findWeatherForCity(String city, Boolean inMetric) {
        StringBuilder requestBuilder;
        requestBuilder = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey);
        if (inMetric) {
            requestBuilder.append("&units=metric");
        }
        String request = requestBuilder.toString();
        return restTemplate.getForObject(
                request,
                Weather.class
        );
    }
}
