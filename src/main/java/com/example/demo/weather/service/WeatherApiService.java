package com.example.demo.weather.service;

import com.example.demo.weather.domain.Weather;
import com.vaadin.flow.component.icon.Icon;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
public class WeatherApiService {
    @Value("${api.weather.key}")
    private String apiKey;

    @Autowired
    private final RestTemplate restTemplate;

    public Weather getWeatherForCity(String cityname){
        return restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/weather?q=" + cityname + "&appid=" + apiKey,
                Weather.class
        );
    }

    public Icon getIconFromResponse(String iconName) {
        return null;
    }
}
