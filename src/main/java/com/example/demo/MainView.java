package com.example.demo;

import com.example.demo.weather.Sys;
import com.example.demo.weather.Weather;
import com.example.demo.weather.WeatherController;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.Objects;

@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private final WeatherController controller;

    public MainView(WeatherController controller) {
        this.controller = controller;
        addClassName("centered-content");
        addComponentAsFirst(new H1("Welcome to weather application"));

        HorizontalLayout center = new HorizontalLayout();
        TextField cityName = new TextField("Type your city");
        center.add(cityName);


        Weather body = controller.readWeatherForWarsaw();
        if (body.getSys().getCountry() != null) {
            String country = body.getSys().getCountry();
            Notification.show("Witamy w " + country);
            cityName.setValue(country);
        }


        add(center);
    }
}
