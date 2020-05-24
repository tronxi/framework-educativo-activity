package es.upm.frameworkeducativoactivity.infrastructure.repository.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivityGroupEntity {
    private String activityId;
    private String groupId;
}
