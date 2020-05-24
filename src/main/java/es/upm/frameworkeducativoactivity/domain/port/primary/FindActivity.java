package es.upm.frameworkeducativoactivity.domain.port.primary;

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult;

import java.util.List;

public interface FindActivity {
    List<ActivityResult> findByGroupId(String groupId);
}
