package es.upm.frameworkeducativoactivity.domain.port.primary;

import es.upm.frameworkeducativoactivity.domain.model.CreateActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.CreateActivityResult;

public interface CreateActivity {

    CreateActivityResult createActivity(CreateActivityOrder createActivityOrder);
}
