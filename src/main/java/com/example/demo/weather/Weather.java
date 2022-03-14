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
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty(value = "base")
    private String base;
    @JsonProperty(value = "visibility")
    private Double temp;
    @JsonProperty(value = "sys")
    private Sys sys;

    public String getBase() {
        return base;
    }

    public Double getTemp() {
        return temp;
    }

    public Sys getSys() {
        return sys;
    }
}
