package es.upm.frameworkeducativoactivity.domain.model;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

@Value
@Builder
public class ActivityResult {
    String activityId;
    String name;
    Timestamp maxDate;
}
