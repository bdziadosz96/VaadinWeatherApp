package com.example.demo.weather.service;

import com.example.demo.weather.domain.Weather;
import com.vaadin.flow.component.html.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
class WeatherApiServiceImpl implements WeatherApiService{
    @Value("${api.weather.key}")
    private String apiKey;

    private final RestTemplate restTemplate;


    public Weather getWeatherForCity(String city){
        return restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey,
                Weather.class
        );
    }

    public Image getIconFromResponse(String iconName){
        String URI = String.format("http://openweathermap.org/img/w/%s.png", iconName);
        return new Image(URI,"alt");
    }
}
