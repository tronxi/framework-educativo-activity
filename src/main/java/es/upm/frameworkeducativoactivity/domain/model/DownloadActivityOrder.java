package es.upm.frameworkeducativoactivity.domain.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DownloadActivityOrder {
    String activityId;
    String studentId;
}
