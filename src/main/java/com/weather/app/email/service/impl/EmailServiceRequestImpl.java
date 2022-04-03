package com.weather.app.email.service.impl;

import com.weather.app.email.config.EmailConfig;
import com.weather.app.email.domain.EmailRequestStatus;
import com.weather.app.email.repository.EmailHistoryRepository;
import com.weather.app.email.service.EmailServiceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class EmailServiceRequestImpl implements EmailServiceRequest {
    private final JavaMailSender javaMailSender;
    private final EmailConfig emailConfig;


    @Override
    public EmailRequestStatus sendEmailCommand(String toEmail, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setText("Hello, here is your daily weather information: " + body);
        message.setSubject("Vaadin-weather request for details information!");
        message.setFrom(emailConfig.getUsername());

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            log.info("Error connected with sending e-mail " + toEmail);
            return EmailRequestStatus.failed();
        }
        return EmailRequestStatus.success();
    }
}
