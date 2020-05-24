package es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.ActivityResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityMapper {

    public ActivityResponse toResponse(ActivityResult activityResult) {
        return ActivityResponse.builder()
                .activityId(activityResult.getActivityId())
                .maxDate(activityResult.getMaxDate())
                .name(activityResult.getName())
                .build();
    }

    public List<ActivityResponse> toResponse(List<ActivityResult> activityResult) {
        return activityResult.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
