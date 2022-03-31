package com.weather.app.email.service.impl;

import com.weather.app.email.config.EmailConfig;
import com.weather.app.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final EmailConfig emailConfig;

    @Override
    public void sendEmail(String toEmail, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setText("Hello, here is your daily weather information: " + body);
        message.setSubject("Vaadin-weather request for details information!");
        message.setFrom(emailConfig.getUsername());

        javaMailSender.send(message);
    }
}
