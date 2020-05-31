package es.upm.frameworkeducativoactivity.domain.port.primary;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryOrder;
import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;

public interface FindDelivery {
    DeliveryResult findById(DeliveryOrder deliveryOrder);
}
