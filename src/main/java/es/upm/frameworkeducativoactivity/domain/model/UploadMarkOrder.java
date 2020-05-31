package es.upm.frameworkeducativoactivity.domain.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UploadMarkOrder {
    String studentId;
    String activityId;
    double mark;
}
