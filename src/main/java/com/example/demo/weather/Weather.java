package com.example.demo.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty(value = "base")
    private String base;
    @JsonProperty(value = "visibility")
    private Double temp;
    @JsonProperty(value = "sys")
    private WeatherSystem sys;
    @JsonProperty(value = "name")
    private String city;
    @JsonProperty(value = "main")
    private WeatherDetails details;

    public String getCity() {
        return city;
    }

    public String getBase() {
        return base;
    }

    public Double getTemp() {
        return temp;
    }

    public WeatherSystem getSys() {
        return sys;
    }

    public WeatherDetails getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "base='" + base + '\'' +
                ", temp=" + temp +
                ", sys=" + sys +
                ", city='" + city + '\'' +
                ", details=" + details +
                '}';
    }
}
