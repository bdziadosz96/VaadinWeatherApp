package com.weather.app.email.service.impl;

import com.weather.app.email.config.EmailConfig;
import com.weather.app.email.repository.EmailHistoryRepository;
import com.weather.app.email.service.EmailServiceRequest;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class EmailServiceRequestImpl implements EmailServiceRequest {
    private final JavaMailSender javaMailSender;
    private final EmailConfig emailConfig;
    private final EmailHistoryRepository repository;

    @Override
    public void sendEmailCommand(String toEmail, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setText("Hello, here is your daily weather information: " + body);
        message.setSubject("Vaadin-weather request for details information!");
        message.setFrom(emailConfig.getUsername());

        javaMailSender.send(message);
    }
}
