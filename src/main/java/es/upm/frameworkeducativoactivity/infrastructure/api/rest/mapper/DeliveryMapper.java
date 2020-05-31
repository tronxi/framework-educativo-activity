package es.upm.frameworkeducativoactivity.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryOrder;
import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;
import es.upm.frameworkeducativoactivity.infrastructure.api.rest.model.DeliveryResponse;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    public DeliveryOrder toDomain(String activityId, String studentId) {
        return DeliveryOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .build();
    }

    public DeliveryResponse toResponse(DeliveryResult deliveryResult) {
        return DeliveryResponse.builder()
                .activityId(deliveryResult.getActivityId())
                .finished(deliveryResult.isFinished())
                .mark(deliveryResult.getMark())
                .userId(deliveryResult.getUserId())
                .build();
    }
}
