package es.upm.frameworkeducativoactivity.infrastructure.api.rest.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class ActivityResponse {
    private String activityId;
    private String name;
    private Timestamp maxDate;
}
