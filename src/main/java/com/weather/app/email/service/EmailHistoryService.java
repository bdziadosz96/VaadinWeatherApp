package com.weather.app.email.service;

public interface EmailHistoryService {
    void saveRequestToDatabase(String emailAddress, String cityName);
}
