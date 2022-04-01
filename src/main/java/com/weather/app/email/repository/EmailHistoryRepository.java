package com.weather.app.email.repository;

import com.weather.app.email.domain.EmailHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailHistoryRepository extends JpaRepository<EmailHistory, Long> {}
