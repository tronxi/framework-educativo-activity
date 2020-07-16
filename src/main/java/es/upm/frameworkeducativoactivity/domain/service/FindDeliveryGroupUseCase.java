package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.FindDeliveryGroup;
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindDeliveryGroupUseCase implements FindDeliveryGroup {

    private final DeliveryRepository deliveryRepository;

    @Override
    public List<DeliveryResult> findById(String activityId) {
        return deliveryRepository.findByActivityId(activityId);
    }
}
