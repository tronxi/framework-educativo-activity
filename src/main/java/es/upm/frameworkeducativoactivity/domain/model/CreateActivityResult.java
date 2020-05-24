package es.upm.frameworkeducativoactivity.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateActivityResult {
    String activityId;
}
