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
        this.temp = convertToCelcius(temp);
        this.tempMin = convertToCelcius(tempMin);
        this.tempMax = convertToCelcius(tempMax);
    }

    private Double convertToCelcius(Double value) {
        value -= 273.15;
        return value;
    }
}
