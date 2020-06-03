package es.upm.frameworkeducativoactivity.domain.port.primary;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;

import java.util.List;

public interface FindDeliveryGroup {
    List<DeliveryResult> findById(String activityId);
}
