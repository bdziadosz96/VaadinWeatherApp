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
public class WeatherTemperatureDetails {
    @JsonProperty("temp")
    private Double temp;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp_min")
    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("humidity")
    private Double humidity;

    @Override
    public String toString() {
        return "temp=" + temp
                + ", feelsLike=" + feelsLike
                + ", tempMin=" + tempMin
                + ", tempMax=" + tempMax
                + ", pressure=" + pressure
                + ", humidity=" + humidity;
    }

    public void convertValuesToCelsius() {
        this.temp = convertToCelsius(temp);
        this.tempMin = convertToCelsius(tempMin);
        this.tempMax = convertToCelsius(tempMax);
    }

    private Double convertToCelsius(Double value) {
        value = (double) Math.round(value - 273.15);
        return value;
    }
}
