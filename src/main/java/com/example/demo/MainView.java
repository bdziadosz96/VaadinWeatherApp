package com.example.demo;

import com.example.demo.weather.Weather;
import com.example.demo.weather.WeatherController;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private final WeatherController controller;

    public MainView(WeatherController controller) {
        this.controller = controller;
        addClassName("centered-content");

        HorizontalLayout center = new HorizontalLayout();

        Weather body = controller.readWeatherForWarsaw().getBody();
        center.add(new TextField("Type your city"));

        add(center);
    }
}
