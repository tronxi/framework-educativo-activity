package es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativoactivity.domain.model.UploadActivityOrder;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.UploadActivityRequest;
import org.springframework.stereotype.Component;

@Component
public class UploadActivityMapper {

    public UploadActivityOrder toDomain(String studentId, String activityId, UploadActivityRequest uploadActivityRequest) {
        return UploadActivityOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .name(uploadActivityRequest.getName())
                .build();
    }
}
