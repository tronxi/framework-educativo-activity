package es.upm.frameworkeducativoactivity.domain.port.secondary;

import es.upm.frameworkeducativoactivity.domain.model.*;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository {

    CreateActivityResult create(CreateActivityOrder createActivityOrder);
    List<ActivityResult> findByGroupId(String groupId);
    void deleteByGroupId(String activityId, String groupId);
    Optional<ActivityResult> findById(String activityId);
    void upload(UploadActivityOrder uploadActivityOrder);
}
