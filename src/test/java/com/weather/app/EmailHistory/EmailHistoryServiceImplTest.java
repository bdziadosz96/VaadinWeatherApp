package com.weather.app.EmailHistory;

import com.weather.app.email.domain.EmailHistory;
import com.weather.app.email.repository.EmailHistoryRepository;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
@Transactional
class EmailHistoryServiceImplTest {

    @Autowired
    EmailHistoryRepository repository;

    @Test
    void findRequestFromDatabase() {
        List<EmailHistory> emptyHistories = repository.findAll();

        assertEquals(emptyHistories.size(),0);
        assertTrue(emptyHistories.isEmpty());
    }

    @Test
    void saveRequestToDatabase() {
        EmailHistory emailHistory = deliverTestReceiver();

        repository.save(emailHistory);

        List<EmailHistory> histories = repository.findAll();

        assertEquals(histories.size(),1);
        assertFalse(histories.isEmpty());
    }

    @Test
    void getRequestFromDatabase() {
        EmailHistory emailHistory = deliverTestReceiver();

        repository.save(emailHistory);

        List<EmailHistory> histories = repository.findAll();
        EmailHistory requestFromDb = histories.get(0);

        assertTrue(Objects.nonNull(requestFromDb));
        assertEquals("test-city", requestFromDb.getCity());
        assertEquals("test-receiver", requestFromDb.getReceiver());
    }

    private EmailHistory deliverTestReceiver() {
        return EmailHistory.builder()
                .city("test-city")
                .receiver("test-receiver")
                .build();
    }
}