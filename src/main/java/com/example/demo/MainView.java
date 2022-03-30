package com.example.demo;

import com.example.demo.email.service.EmailService;
import com.example.demo.weather.domain.Weather;
import com.example.demo.weather.service.WeatherApiService;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import java.util.List;

@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    private final WeatherApiService service;
    private final EmailService emailService;
    private List<HasValue> activeComponentList;
    private NumberField temperature;
    private NumberField minTemperature;
    private NumberField maxTemperature;
    private NumberField humidity;
    private NumberField pressure;
    private TextField state;
    private TextField cityName;
    private Binder<Weather> binder;
    private Weather weather;
    private Button searchButton;
    private Button resetButton;
    private Button emailSubscriptionButton;
    private EmailField emailField;
    private TextField baseCity;
    private Dialog emailDialog;

    public MainView(WeatherApiService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;

        initMainLayout();

        Image logo = loadImageFromPath("/images/myimage.png");

        baseCity = initCitySearchField();

        HorizontalLayout controlLayout = initControlLayout();

        HorizontalLayout graph = initGraphLayout();

        emailDialog = initEmailLayout();
        configureListeners();
        configureGraph(graph);
        add(logo, baseCity, controlLayout, graph);
    }

    private Dialog initEmailLayout() {
        emailDialog = new Dialog();
        VerticalLayout emailLayout = new VerticalLayout();
        emailLayout.add(new H2("Type your email address"));
        emailField = new EmailField("Write your email here");
        emailField.addClassName("email-content");
        emailLayout.add(emailField);
        emailLayout.add(new Button("Send"));
        emailLayout.add(new Button("Close"));
        emailDialog.add(emailLayout);
        return emailDialog;
    }

    private void configureListeners() {
        searchButton.addClickListener(click -> checkDetailIn(baseCity));
        resetButton.addClickListener(click -> clearForm());
        emailSubscriptionButton.addClickListener(click -> emailDialog.open());
    }

    private void configureGraph(HorizontalLayout graph) {
        graph.setAlignItems(Alignment.CENTER);
        graph.addClassName("graph-content");
        activeComponentList = List.of(temperature,state,cityName,humidity,pressure,maxTemperature,minTemperature);
        activeComponentList
                        .forEach(component -> component.setReadOnly(true));
        graph.add(temperature, state, cityName, humidity, pressure, minTemperature, maxTemperature);
    }

    private HorizontalLayout initGraphLayout() {
        HorizontalLayout graph = new HorizontalLayout();
        temperature = new NumberField("Temperature");
        state = new TextField("State");
        cityName = new TextField("City");
        humidity = new NumberField("Humidity");
        pressure = new NumberField("Pressure");
        minTemperature = new NumberField("Min Temperature");
        maxTemperature = new NumberField("Max Temperature");
        return graph;
    }

    private TextField initCitySearchField() {
        TextField baseCity = new TextField("City");
        baseCity.setPlaceholder("Check weather in...");
        return baseCity;
    }

    private HorizontalLayout initControlLayout() {
        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.addClassName("control-content");
        searchButton = new Button("Search", new Icon(VaadinIcon.SEARCH));
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        resetButton = new Button("Reset", new Icon(VaadinIcon.BACKSPACE));
        resetButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        emailSubscriptionButton = new Button("Email details", new Icon(VaadinIcon.MAILBOX));
        emailSubscriptionButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        controlLayout.add(searchButton, resetButton, emailSubscriptionButton);
        return controlLayout;
    }

    private void initMainLayout() {
        addClassName("centered-content");
        setWidthFull();
        binder = new Binder<>(Weather.class, true);

        add(new H2("Welcome to weather application"));
    }

    private void clearForm() {
        binder.getFields()
                .forEach(HasValue::clear);
        activeComponentList
                        .forEach(HasValue::clear);
        binder.setBean(null);
    }

    private void checkDetailIn(TextField baseCity) {
        weather = this.service.getWeatherForCity(baseCity.getValue());
        try {
            binder.writeBean(weather);
            System.out.println(weather);
            temperature.setValue(weather.getDetails().getTemp());
            state.setValue(weather.getSys().getCountry());
            cityName.setValue(weather.getCity());
            humidity.setValue(weather.getDetails().getHumidity());
            pressure.setValue(weather.getDetails().getPressure());
            minTemperature.setValue(weather.getDetails().getTempMin());
            maxTemperature.setValue(weather.getDetails().getTempMax());
        } catch (ValidationException ex) {
            ex.printStackTrace();
        }
    }


    private Image loadImageFromPath(String path) {
        StreamResource imageResource = new StreamResource("myimage.png",
                () -> getClass().getResourceAsStream(path));
        Image image = new Image(imageResource, "My Streamed Image");
        image.setWidth("75%");
        image.setHeight("75%");
        return image;
    }
}
