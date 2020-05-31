package es.upm.frameworkeducativoactivity.infrastructure.repository;

import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult;
import es.upm.frameworkeducativoactivity.domain.model.UploadMarkOrder;
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository;
import es.upm.frameworkeducativoactivity.infrastructure.repository.dao.ActivityUserEntityDao;
import es.upm.frameworkeducativoactivity.infrastructure.repository.entity.ActivityUserEntity;
import es.upm.frameworkeducativoactivity.infrastructure.repository.mapper.ActivityUserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeliveryRepositoryAdapter implements DeliveryRepository {

    private final ActivityUserEntityDao activityUserEntityDao;
    private final ActivityUserEntityMapper activityUserEntityMapper;

    @Override
    public Optional<DeliveryResult> findByActivityIdAndStudentId(String activityId, String studentId) {
        Optional<ActivityUserEntity> activityUserEntity =
                Optional.ofNullable(activityUserEntityDao.findByStudentIdAndActivityId(studentId, activityId));
        return activityUserEntity.map(activityUserEntityMapper::toDomain);
    }

    @Override
    public void uploadMark(UploadMarkOrder uploadMarkOrder) {
        activityUserEntityDao.uploadMark(uploadMarkOrder.getMark(), uploadMarkOrder.getActivityId(), uploadMarkOrder.getStudentId());
    }
}
