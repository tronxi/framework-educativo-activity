package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteGroup;
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteGroupUseCase implements DeleteGroup {

    private final DeliveryRepository deliveryRepository;

    @Override
    public void deleteGroupById(String groupId) {
        deliveryRepository.deleteByGroupId(groupId);
    }
}
