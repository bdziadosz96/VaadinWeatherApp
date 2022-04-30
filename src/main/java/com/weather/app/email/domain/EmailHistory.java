package com.weather.app.email.domain;

import com.weather.app.jpa.AbstractEntity;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.atmosphere.config.service.Get;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EmailHistory extends AbstractEntity {
    private String city;
    private String receiver;
}
