package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.model.CreateActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.CreateActivityResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.CreateActivity;
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateActivityUseCase implements CreateActivity {

    private final ActivityRepository activityRepository;

    @Override
    public CreateActivityResult createActivity(CreateActivityOrder createActivityOrder) {
        return activityRepository.create(createActivityOrder);
    }
}
