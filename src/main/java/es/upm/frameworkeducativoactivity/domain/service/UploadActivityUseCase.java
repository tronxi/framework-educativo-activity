package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult;
import es.upm.frameworkeducativoactivity.domain.model.UploadActivityOrder;
import es.upm.frameworkeducativoactivity.domain.model.UploadActivityResult;
import es.upm.frameworkeducativoactivity.domain.port.primary.UploadActivity;
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class UploadActivityUseCase implements UploadActivity {

    private final ActivityRepository activityRepository;

    @Override
    public UploadActivityResult uploadActivity(UploadActivityOrder uploadActivityOrder) {
        ActivityResult activityResult = activityRepository
                .findById(uploadActivityOrder.getActivityId())
                .orElseThrow(RuntimeException::new);

        activityRepository.upload(uploadActivityOrder);

        if (ifDateIsValid(activityResult)) {
            return koResponse();
        }

        return okResponse();
    }

    private boolean ifDateIsValid(ActivityResult activityResult) {
        return activityResult.getMaxDate().before(new Date(System.currentTimeMillis()));
    }

    private UploadActivityResult okResponse()  {
        return UploadActivityResult.builder()
                .done(true)
                .build();
    }

    private UploadActivityResult koResponse()  {
        return UploadActivityResult.builder()
                .done(false)
                .build();
    }
}
