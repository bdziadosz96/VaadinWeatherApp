package com.weather.app.EmailHistory;

import com.weather.app.email.config.EmailConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
@AutoConfigureTestDatabase
class EmailServiceRequestImplTest {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    EmailConfig emailConfig;

    @Test
    public void blankAddressThrowException() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("");
        message.setText("test@test.pl");
        message.setSubject("Vaadin-weather request for details information!");
        message.setFrom(emailConfig.getUsername());

        Assertions.assertThrows(MailParseException.class, () -> javaMailSender.send(message));
    }

    @Test
    public void blankThemeThrowException() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("test@test.pl");
        message.setSubject("Vaadin-weather request for details information!");

        Assertions.assertThrows(MailSendException.class, () -> javaMailSender.send(message));
    }
}