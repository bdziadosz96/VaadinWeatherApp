package com.weather.app;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.weather.app.email.domain.EmailRequestStatus;
import com.weather.app.email.service.EmailHistoryService;
import com.weather.app.email.service.EmailServiceRequest;
import com.weather.app.weather.domain.Weather;
import com.weather.app.weather.domain.WeatherTemperatureDetails;
import com.weather.app.weather.service.WeatherApiService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    private final WeatherApiService service;
    private final EmailServiceRequest emailService;
    private final EmailHistoryService historyService;
    private List<HasValue> activeComponentList;
    private EmailField emailField;
    private NumberField temperature;
    private NumberField minTemperature;
    private NumberField maxTemperature;
    private NumberField humidity;
    private NumberField pressure;
    private TextField state;
    private TextField cityName;
    private TextField baseCity;
    private Dialog emailDialog;
    private Weather weather;
    private Button searchButton;
    private Button resetButton;
    private Button emailSubscriptionButton;
    private Binder<Weather> binder;
    private EmailRequestStatus emailRequestStatus;

    public MainView(final WeatherApiService service, final EmailServiceRequest emailService, EmailHistoryService historyService) {
        this.service = service;
        this.emailService = emailService;
        this.historyService = historyService;

        initMainLayout();

        final Image logo = loadImageFromPath("/images/myimage.png");

        baseCity = initCitySearchField();

        final HorizontalLayout controlLayout = initControlLayout();

        final HorizontalLayout graph = initGraphLayout();

        configureListeners();
        configureGraph(graph);
        add(logo, baseCity, controlLayout, graph);
    }

    private Dialog initEmailLayout() {
        emailDialog = new Dialog();
        VerticalLayout emailLayout = new VerticalLayout();
        configureEmailLayout(emailLayout);
        emailLayout.add(emailField);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        configureButtonLayout(buttonLayout);

        emailDialog.add(emailLayout, buttonLayout);
        emailDialog.open();
        return emailDialog;
    }

    private void configureButtonLayout(HorizontalLayout buttonLayout) {
        Button sendButton = configureSendButton();
        Button closeButton = configureCloseButton();
        buttonLayout.add(sendButton, closeButton);
        buttonLayout.setClassName("email-button-content");
        sendButton.addClickListener(click -> handleEmailRequest());
    }

    private void handleEmailRequest() {
        String addressEmail = emailField.getValue();
        String weatherBody = weather.toString();
        var status = emailService.sendEmailCommand(addressEmail, weatherBody);
        handleRequestStatus(status, addressEmail);
    }

    private void handleRequestStatus(EmailRequestStatus status, String addressEmail) {
        String city = cityName.getValue();
        if (StringUtils.equals(status.getCode(),"SUCCESS")) {
            historyService.saveRequestToDatabase(addressEmail,city);
        } else {
            Notification.show("We cannot proceed your request",1000, Notification.Position.MIDDLE);
        }
    }

    private Button configureCloseButton() {
        Button close = new Button("Close");
        close.addClickListener(click -> emailDialog.close());
        return close;
    }

    private Button configureSendButton() {
        return new Button("Send");
    }

    private void configureEmailLayout(VerticalLayout emailLayout) {
        emailLayout.add(new H2("Type your email address"));
        emailField = new EmailField("Write your email here");
        emailField.addClassName("email-content");
    }

    private void configureListeners() {
        searchButton.addClickListener(click -> checkDetailIn(baseCity));
        resetButton.addClickListener(click -> clearForm());
        emailSubscriptionButton.addClickListener(click -> emailDialog.open());
    }

    private void configureGraph(final HorizontalLayout graph) {
        graph.setAlignItems(Alignment.CENTER);
        graph.addClassName("graph-content");
        activeComponentList = List.of(temperature, state, cityName, humidity, pressure, maxTemperature, minTemperature);
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
        baseCity = new TextField("City");
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
        emailSubscriptionButton = new Button("Send Email", new Icon(VaadinIcon.MAILBOX),
                click -> emailDialog = initEmailLayout());
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
        baseCity.clear();
        binder.setBean(null);
    }

    private void checkDetailIn(final TextField baseCity) {
        String city = baseCity.getValue();
        if (StringUtils.isBlank(city) || StringUtils.isEmpty(city)) {
            Notification.show("City cannot be empty!",1000, Notification.Position.MIDDLE);
        }
        else {
            weather = this.service.findWeatherForCity(city);
            WeatherTemperatureDetails weatherDetails = weather.getDetails();
            try {
                binder.writeBean(weather);
                System.out.println(weather);
                temperature.setValue(weatherDetails.getTemp());
                state.setValue(weather.getSys().getCountry());
                cityName.setValue(weather.getCity());
                humidity.setValue(weatherDetails.getHumidity());
                pressure.setValue(weatherDetails.getPressure());
                minTemperature.setValue(weatherDetails.getTempMin());
                maxTemperature.setValue(weatherDetails.getTempMax());
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
        }
    }


    private Image loadImageFromPath(final String path) {
        StreamResource imageResource = new StreamResource("myimage.png",
                () -> getClass().getResourceAsStream(path));
        Image image = new Image(imageResource, "My Streamed Image");
        image.setWidth("75%");
        image.setHeight("75%");
        return image;
    }
}
