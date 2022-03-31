package com.weather.app.weather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty(value = "name")
    private String city;
    @JsonProperty(value = "sys")
    private WeatherSystemDetails sys;
    @JsonProperty(value = "main")
    private WeatherTemperatureDetails details;
    @JsonProperty(value = "weather")
    private List<WeatherApiDetails> weathers;

    @Override
    public String toString() {
        return "city=" + city + " " + sys
                + " details=" + details;
    }
}
