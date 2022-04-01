package com.weather.app.jpa;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    @Id
    @GeneratedValue
    private String uuid = UUID.randomUUID().toString();

    @Version
    private Integer version;

    @CreatedDate
    private LocalDate createdAt;
}


