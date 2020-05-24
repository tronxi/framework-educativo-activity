package es.upm.frameworkeducativoactivity.infrastructure.repository.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class ActivityEntity {
    private String activityId;
    private String name;
    private Timestamp maxDate;
}
