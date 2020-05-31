package es.upm.frameworkeducativoactivity.domain.port.primary;

import es.upm.frameworkeducativoactivity.domain.model.UploadActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.UploadActivityResult;

public interface UploadActivity {
    UploadActivityResult uploadActivity(UploadActivityOrder uploadActivityOrder);
}
