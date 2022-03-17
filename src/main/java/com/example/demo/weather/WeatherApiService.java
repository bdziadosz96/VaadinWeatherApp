package com.example.demo.weather;

import com.example.demo.weather.domain.Weather;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@AllArgsConstructor
@Service
public class WeatherApiService {
    @Value("${api.weather.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public Weather readWeatherForCity(String cityname) {
        Weather weather = restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/weather?q=" + cityname + "&appid=" + apiKey,
                Weather.class
        );
        return weather;
    }
}
