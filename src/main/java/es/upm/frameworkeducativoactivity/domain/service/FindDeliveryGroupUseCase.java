package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.FindDeliveryGroup;
import es.upm.frameworkeducativoactivity.infrastructure.repository.DeliveryRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindDeliveryGroupUseCase implements FindDeliveryGroup {

    private final DeliveryRepositoryAdapter deliveryRepositoryAdapter;

    @Override
    public List<DeliveryResult> findById(String activityId) {
        return deliveryRepositoryAdapter.findByActivityId(activityId);
    }
}
