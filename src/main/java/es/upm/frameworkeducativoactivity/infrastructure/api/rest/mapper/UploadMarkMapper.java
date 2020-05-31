package es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativoactivity.domain.model.UploadMarkOrder;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.UploadMarkRequest;
import org.springframework.stereotype.Component;

@Component
public class UploadMarkMapper {

    public UploadMarkOrder toDomain(String studentId, String activityId, UploadMarkRequest uploadMarkRequest) {
        return UploadMarkOrder.builder()
                .studentId(studentId)
                .mark(uploadMarkRequest.getMark())
                .activityId(activityId)
                .build();
    }
}
