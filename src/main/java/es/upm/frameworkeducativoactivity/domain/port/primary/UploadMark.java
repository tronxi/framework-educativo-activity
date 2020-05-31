package es.upm.frameworkeducativoactivity.domain.port.primary;

import es.upm.frameworkeducativoactivity.domain.model.UploadMarkOrder;

public interface UploadMark {
    void upload(UploadMarkOrder uploadMarkOrder);
}
