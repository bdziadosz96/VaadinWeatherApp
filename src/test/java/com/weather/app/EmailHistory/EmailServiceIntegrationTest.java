package com.weather.app.EmailHistory;

import com.weather.app.email.domain.EmailHistory;
import com.weather.app.email.domain.EmailRequestStatus;
import com.weather.app.email.service.EmailHistoryService;
import com.weather.app.email.service.EmailServiceQuery;
import com.weather.app.email.service.EmailServiceRequest;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static com.weather.app.email.domain.EmailRequestStatus.failed;
import static com.weather.app.email.domain.EmailRequestStatus.success;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class EmailServiceIntegrationTest {
    @Autowired
    EmailServiceRequest emailServiceRequest;

    @Autowired
    EmailHistoryService emailHistoryService;

    @Autowired
    EmailServiceQuery emailHistoryQuery;

    @Test
    public void sendEmailAndHistoryIsCreated() {
        emailServiceRequest.sendEmailCommand("test","test");
        emailHistoryService.saveRequestToDatabase("test","test");

        List<EmailHistory> allEmail = emailHistoryQuery.findAllEmail();
        EmailHistory emailHistory = allEmail.get(0);

        Assertions.assertEquals(emailHistory.getReceiver(),"test");
        Assertions.assertTrue(emailHistory.getCity().contains("test"));
    }

    @Test
    public void sendEmailSuccessfulReturnSuccess() {
        String successStatusCode = success().getCode();
        EmailRequestStatus emailRequestStatus = emailServiceRequest.sendEmailCommand("test@test.pl", "test");
        emailHistoryService.saveRequestToDatabase("test","test");

        String receivedRequestCode = emailRequestStatus.getCode();
        Assertions.assertEquals(successStatusCode,receivedRequestCode);
    }

    @Test
    public void sendEmailFailureReturnFail() {
        String successStatusCode = failed().getCode();
        EmailRequestStatus emailRequestStatus = emailServiceRequest.sendEmailCommand("", "");
        emailHistoryService.saveRequestToDatabase("test","test");

        String receivedRequestCode = emailRequestStatus.getCode();
        Assertions.assertEquals(successStatusCode,receivedRequestCode);
    }
}
