package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.model.UploadMarkOrder;
import es.upm.frameworkeducativoactivity.domain.port.primary.UploadMark;
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository;
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploadMarkUseCase implements UploadMark {

    private final DeliveryRepository deliveryRepository;
    private final ActivityRepository activityRepository;

    @Override
    public void upload(UploadMarkOrder uploadMarkOrder) {

        activityRepository
                .findById(uploadMarkOrder.getActivityId())
                .orElseThrow(RuntimeException::new);

        deliveryRepository
                .findByActivityIdAndStudentId(uploadMarkOrder.getActivityId(), uploadMarkOrder.getStudentId())
                .orElseThrow(RuntimeException::new);
        if (checkMarkRange(uploadMarkOrder)) {
            throw new RuntimeException("out of range");
        }
        deliveryRepository.uploadMark(uploadMarkOrder);
    }

    private boolean checkMarkRange(UploadMarkOrder uploadMarkOrder) {
        return uploadMarkOrder.getMark() < 0 || uploadMarkOrder.getMark() > 10;
    }
}
