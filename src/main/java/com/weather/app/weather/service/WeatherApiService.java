package com.weather.app.weather.service;

import com.weather.app.weather.domain.Weather;
import com.vaadin.flow.component.html.Image;

public interface WeatherApiService {
    Weather findWeatherForCity(String city);

    Image getIconFromResponse(String iconName);
}
