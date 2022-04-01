package com.weather.app.email.service;

public interface EmailServiceRequest {
    void sendEmailCommand(String toEmail,
                          String body);
}
