package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryOrder;
import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.FindDelivery;
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindDeliveryUseCase implements FindDelivery {

    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryResult findById(DeliveryOrder deliveryOrder) {
        
        Optional<DeliveryResult> deliveryResult = deliveryRepository
                .findByActivityIdAndStudentId(deliveryOrder.getActivityId(), deliveryOrder.getStudentId());

        return deliveryResult.orElseThrow(RuntimeException::new);
    }
}
