package es.upm.frameworkeducativoactivity.domain.port.secondary;

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult;
import es.upm.frameworkeducativoactivity.domain.model.CreateActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.CreateActivityResult;

import java.util.List;

public interface ActivityRepository {

    CreateActivityResult create(CreateActivityOrder createActivityOrder);
    List<ActivityResult> findByGroupId(String groupId);
    void deleteByGroupId(String activityId, String groupId);
}
