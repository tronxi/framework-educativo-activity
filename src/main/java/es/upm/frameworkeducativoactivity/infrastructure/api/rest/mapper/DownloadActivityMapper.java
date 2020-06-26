package es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityOrder;
import org.springframework.stereotype.Component;

@Component
public class DownloadActivityMapper {

    public DownloadActivityOrder toDomain(String activityId, String studentId) {
        return DownloadActivityOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .build();
    }
}
