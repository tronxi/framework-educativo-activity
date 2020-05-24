package es.upm.frameworkeducativoactivity.infrastructure.repository.mapper;

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult;
import es.upm.frameworkeducativoactivity.domain.model.CreateActivityOrder;
import es.upm.frameworkeducativoactivity.infrastructure.repository.entity.ActivityEntity;
import org.springframework.stereotype.Component;

@Component
public class ActivityEntityMapper {

    public ActivityEntity toActivityEntity(CreateActivityOrder createActivityOrder, String activityId) {
        return ActivityEntity.builder()
                .activityId(activityId)
                .maxDate(createActivityOrder.getMaxDate())
                .name(createActivityOrder.getName())
                .build();
    }

    public ActivityResult toDomain(ActivityEntity activityEntity) {
        return ActivityResult.builder()
                .activityId(activityEntity.getActivityId())
                .maxDate(activityEntity.getMaxDate())
                .name(activityEntity.getName())
                .build();
    }
}
