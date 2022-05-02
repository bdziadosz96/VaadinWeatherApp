package com.weather.app.Weather;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;


@SpringBootTest
@AutoConfigureTestDatabase
class WeatherApiServiceTest {

    String dummyKey = "dummyKey";
    String apiKey = "";

    @Test
    public void getWeatherForWarsawWithDummyKey() throws IOException {
        HttpUriRequest request = new HttpGet("https://api.openweathermap.org/data/2.5/w" +
                "eather?q=Warsaw&appid="+dummyKey+"&units=metric");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assertions.assertEquals(401,httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void getWeatherForWarsawWithRealKey() throws IOException {
        HttpUriRequest request = new HttpGet("https://api.openweathermap.org/data/2.5/w" +
                "eather?q=Warsaw&appid="+apiKey+"&units=metric");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        ReflectionTestUtils.setField(apiKey, "myProperty", "apiKey");

        Assertions.assertEquals(200,httpResponse.getStatusLine().getStatusCode());

    }

}
