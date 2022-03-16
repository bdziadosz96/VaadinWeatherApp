package com.example.demo.weather;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {
    @Value("${api.weather.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Weather readWeatherForCity(String cityname) {
        Weather weather = restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/weather?q="+cityname+"&appid="+apiKey,
                Weather.class
        );
        return weather;
    }
}

/*
api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
 */