package com.weather.app.weather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherTemperatureDetails {
    @JsonProperty("temp")
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    @JsonProperty("pressure")
    private double pressure;
    @JsonProperty("humidity")
    private double humidity;

    @Override
    public String toString() {
        return "temp=" + temp
                + ", feelsLike=" + feelsLike
                + ", tempMin=" + tempMin
                + ", tempMax=" + tempMax
                + ", pressure=" + pressure
                + ", humidity=" + humidity;
    }
}
