package com.weather.app.email.domain;

import com.weather.app.jpa.AbstractEntity;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailHistory extends AbstractEntity {
    private String city;
    private String receiver;
}
