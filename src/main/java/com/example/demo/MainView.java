package com.example.demo;

import com.example.demo.weather.domain.Weather;
import com.example.demo.weather.service.WeatherApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    private final WeatherApiService service;
    private NumberField temperature;
    private NumberField minTemperature;
    private NumberField maxTemperature;
    private TextField humidity;
    private TextField pressure;
    private NumberField state;
    private TextField cityName;
    private Binder<Weather> binder;
    private Weather weather;

    public MainView(WeatherApiService controller) {
        this.service = controller;
        addClassName("centered-content");
        setWidthFull();
        binder = new Binder<>(Weather.class, true);

        add(new H2("Welcome to weather application"));
        Image image = loadImageFromPath("/images/myimage.png");


        TextField baseCity = new TextField("City");
        baseCity.setPlaceholder("Check weather...");

        HorizontalLayout controlLayout = new HorizontalLayout();
        Button searchButton = new Button("Search", new Icon(VaadinIcon.SEARCH));
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button resetButton = new Button("Reset", new Icon(VaadinIcon.WARNING));
        resetButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        controlLayout.add(searchButton, resetButton);
        Image iconImage = new Image("http://openweathermap.org/img/w/10n.png","alt");
        add(iconImage);

        HorizontalLayout graph = new HorizontalLayout();
        temperature = new NumberField("Temperature");
        state = new NumberField("State");
        cityName = new TextField("City");


        searchButton.addClickListener(e -> checkDetailIn(controller, baseCity));

        graph.addClassName("my-content");
        graph.add(temperature, state, cityName);

        add(image, baseCity, controlLayout, graph);
    }

    private void checkDetailIn(WeatherApiService controller, TextField baseCity) {
        weather = controller.getWeatherForCity(baseCity.getValue());
        try {
            binder.writeBean(weather);
            System.out.println(weather);
            temperature.setValue(weather.getDetails().getTemp());
            state.setValue(weather.getDetails().getHumidity());
            cityName.setValue(weather.getCity());
        } catch (ValidationException ex) {
            ex.printStackTrace();
        }
    }

    //
    private Image loadImageFromPath(String path) {
        StreamResource imageResource = new StreamResource("myimage.png",
                () -> getClass().getResourceAsStream(path));
        Image image = new Image(imageResource, "My Streamed Image");

        image.setWidth("75%");
        image.setHeight("75%");
        return image;
    }
}
