package com.example.demo.email.service;

import org.springframework.stereotype.Service;

public interface EmailService {
    void sendEmail(String toEmail,
                   String body);
}
