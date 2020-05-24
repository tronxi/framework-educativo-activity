package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.FindActivity;
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindActivityUseCase implements FindActivity {

    private final ActivityRepository activityRepository;
    @Override
    public List<ActivityResult> findByGroupId(String groupId) {
        return activityRepository.findByGroupId(groupId);
    }
}
