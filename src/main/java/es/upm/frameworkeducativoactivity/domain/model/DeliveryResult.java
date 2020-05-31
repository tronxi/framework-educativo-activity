package es.upm.frameworkeducativoactivity.domain.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DeliveryResult {
    String activityId;
    String userId;
    double mark;
    boolean finished;
}
