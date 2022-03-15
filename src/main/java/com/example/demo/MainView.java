package com.example.demo;

import com.example.demo.weather.Weather;
import com.example.demo.weather.WeatherController;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    private TextField temperature;
    private TextField state;
    private TextField cityName;

    private final WeatherController controller;

    public MainView(WeatherController controller) {
        this.controller = controller;
        addClassName("centered-content");
        setWidthFull();

        add(new H2("Welcome to weather application"));
        StreamResource imageResource = new StreamResource("myimage.png",
                () -> getClass().getResourceAsStream("/images/myimage.png"));
        Image image = new Image(imageResource, "My Streamed Image");

        image.setWidth("75%");
        image.setHeight("75%");

        HorizontalLayout center = new HorizontalLayout();
        Weather weather = controller.readWeatherForWarsaw();
        temperature = new TextField("Temperature", String.valueOf(weather.getTemp()));
        state = new TextField("State", weather.getSys().getCountry());
        cityName = new TextField("City", weather.getCity());

        center.addClassName("my-content");
        center.add(temperature, state, cityName);

        add(image, center);
    }
}
