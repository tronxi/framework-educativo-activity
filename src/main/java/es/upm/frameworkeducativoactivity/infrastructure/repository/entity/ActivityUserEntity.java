package es.upm.frameworkeducativoactivity.infrastructure.repository.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ActivityUserEntity {

    private String activityId;
    private String userId;
    private double mark;
    private int finished;

}
