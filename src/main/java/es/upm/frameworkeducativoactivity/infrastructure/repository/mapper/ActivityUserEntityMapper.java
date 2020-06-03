package es.upm.frameworkeducativoactivity.infrastructure.repository.mapper;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;
import es.upm.frameworkeducativoactivity.infrastructure.repository.entity.ActivityUserEntity;
import org.springframework.stereotype.Component;

@Component
public class ActivityUserEntityMapper {
    public DeliveryResult toDomain(ActivityUserEntity activityUserEntity) {
        return DeliveryResult.builder()
                .activityId(activityUserEntity.getActivityId())
                .finished(activityUserEntity.getFinished() == 1)
                .mark(activityUserEntity.getMark() == null ? 0 : activityUserEntity.getMark())
                .userId(activityUserEntity.getUserId())
                .build();
    }

}
