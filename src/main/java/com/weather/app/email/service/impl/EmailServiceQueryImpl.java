package com.weather.app.email.service.impl;

import com.weather.app.email.domain.EmailHistory;
import com.weather.app.email.repository.EmailHistoryRepository;
import com.weather.app.email.service.EmailServiceQuery;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class EmailServiceQueryImpl implements EmailServiceQuery {
    private final EmailHistoryRepository repository;

    @Override
    public List<EmailHistory> findAllEmail() {
        return repository.findAll();
    }
}
