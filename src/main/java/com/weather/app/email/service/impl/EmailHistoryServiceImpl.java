package com.weather.app.email.service.impl;

import com.weather.app.email.domain.EmailHistory;
import com.weather.app.email.repository.EmailHistoryRepository;
import com.weather.app.email.service.EmailHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class EmailHistoryServiceImpl implements EmailHistoryService {
    private final EmailHistoryRepository repository;

    @Override
    public void saveRequestToDatabase(String emailAddress, String cityName) {
        log.info("Save request address: " + emailAddress + ", city: " + cityName);
        repository.save(toEmailHistory(emailAddress,cityName));
    }

    private EmailHistory toEmailHistory(String emailAddress, String cityName) {
        return EmailHistory.builder()
                .city(cityName)
                .receiver(emailAddress)
                .build();
    }
}
