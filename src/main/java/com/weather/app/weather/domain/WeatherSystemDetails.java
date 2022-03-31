package com.weather.app.weather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherSystemDetails {
    @JsonProperty("type")
    private int type;
    @JsonProperty("id")
    private int id;
    @JsonProperty("country")
    private String country;
    @JsonProperty("sunrise")
    private int sunrise;
    @JsonProperty("sunset")
    private int sunset;

    @Override
    public String toString() {
        return " country= " + country;
    }
}
