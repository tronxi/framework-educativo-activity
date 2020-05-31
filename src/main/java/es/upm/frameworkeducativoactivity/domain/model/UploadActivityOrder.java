package es.upm.frameworkeducativoactivity.domain.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UploadActivityOrder {
    String name;
    String studentId;
    String activityId;
}
