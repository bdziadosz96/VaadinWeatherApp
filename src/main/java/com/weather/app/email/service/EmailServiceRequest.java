package com.weather.app.email.service;

import com.weather.app.email.domain.EmailRequestStatus;

public interface EmailServiceRequest {
    EmailRequestStatus sendEmailCommand(String toEmail,
                                        String body);
}
