package es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativoactivity.domain.model.CreateActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.CreateActivityResult;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.CreateActivityRequest;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.CreateActivityResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateActivityMapper {

    public CreateActivityOrder toDomain(CreateActivityRequest createActivityRequest) {
        return CreateActivityOrder.builder()
                .groupId(createActivityRequest.getGroupId())
                .name(createActivityRequest.getName())
                .maxDate(createActivityRequest.getMaxDate())
                .build();
    }

    public CreateActivityResponse toResponse(CreateActivityResult createActivityResult) {
        return CreateActivityResponse.builder()
                .activityId(createActivityResult.getActivityId())
                .build();
    }
}
