package com.weather.app.email.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EmailRequestStatus {
    private String code;

    public static EmailRequestStatus success() {
        return  EmailRequestStatus.builder()
                .code("SUCCESS")
                .build();
    }

    public static EmailRequestStatus failed() {
        return  EmailRequestStatus.builder()
                .code("FAILED")
                .build();
    }
}
