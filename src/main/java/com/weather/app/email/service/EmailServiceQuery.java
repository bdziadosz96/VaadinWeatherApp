package com.weather.app.email.service;

import com.weather.app.email.domain.EmailHistory;
import java.util.List;

public interface EmailServiceQuery {
    List<EmailHistory> findAllEmail();
}
