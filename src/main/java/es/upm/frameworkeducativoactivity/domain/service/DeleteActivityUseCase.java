package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteActivity;
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteActivityUseCase implements DeleteActivity {

    private final ActivityRepository activityRepository;

    @Override
    public void deleteByGroupId(String activityId, String groupId) {
        activityRepository.deleteByGroupId(activityId, groupId);
    }
}
