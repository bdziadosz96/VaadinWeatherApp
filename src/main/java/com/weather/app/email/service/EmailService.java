package com.weather.app.email.service;

public interface EmailService {
    void sendEmail(String toEmail,
                   String body);
}
