package es.upm.frameworkeducativoactivity.infrastructure.api.rest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeliveryResponse {
    String activityId;
    String userId;
    double mark;
    boolean finished;
}
