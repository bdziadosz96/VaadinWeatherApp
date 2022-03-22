package com.example.demo.weather.service;

import com.example.demo.weather.domain.Weather;
import com.vaadin.flow.component.html.Image;

interface WeatherApiService {
    Weather getWeatherForCity(String city);
    Image getIconFromResponse(String iconName);
}
