package es.upm.frameworkeducativoactivity.domain.port.secondary;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;
import es.upm.frameworkeducativoactivity.domain.model.UploadMarkOrder;

import java.util.Optional;

public interface DeliveryRepository {
    Optional<DeliveryResult> findByActivityIdAndStudentId(String activityId, String studentId);
    void uploadMark(UploadMarkOrder uploadMarkOrder);
}
