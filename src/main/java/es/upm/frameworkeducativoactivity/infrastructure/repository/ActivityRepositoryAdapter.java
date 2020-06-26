package es.upm.frameworkeducativoactivity.infrastructure.repository;

import es.upm.frameworkeducativoactivity.domain.model.*;
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository;
import es.upm.frameworkeducativoactivity.infrastructure.repository.dao.ActivityEntityDao;
import es.upm.frameworkeducativoactivity.infrastructure.repository.dao.ActivityGroupEntityDao;
import es.upm.frameworkeducativoactivity.infrastructure.repository.dao.ActivityUserEntityDao;
import es.upm.frameworkeducativoactivity.infrastructure.repository.entity.ActivityEntity;
import es.upm.frameworkeducativoactivity.infrastructure.repository.entity.ActivityGroupEntity;
import es.upm.frameworkeducativoactivity.infrastructure.repository.mapper.ActivityEntityMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ActivityRepositoryAdapter implements ActivityRepository {

    private final ActivityEntityMapper activityEntityMapper;
    private final ActivityEntityDao activityEntityDao;
    private final ActivityGroupEntityDao activityGroupEntityDao;
    private final ActivityUserEntityDao activityUserEntityDao;
    private final S3Repository s3Repository;

    @Override
    public CreateActivityResult create(CreateActivityOrder createActivityOrder) {

        String activityId = UUID.randomUUID().toString();
        ActivityEntity activityEntity = activityEntityMapper.toActivityEntity(createActivityOrder, activityId);

        try {
            activityEntityDao.saveActivity(activityEntity.getActivityId(), activityEntity.getName(), activityEntity.getMaxDate());
            List<ActivityGroupEntity> activityGroupEntityList = createActivityOrder.getGroupId().stream()
                    .map(groupId -> ActivityGroupEntity.builder()
                            .activityId(activityId)
                            .groupId(groupId)
                            .build())
                    .collect(Collectors.toList());

            activityGroupEntityList
                    .forEach(ag
                            -> activityGroupEntityDao
                            .saveActivityGroupList(ag.getActivityId(), ag.getGroupId()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return CreateActivityResult.builder().activityId(activityId).build();

    }

    @Override
    public List<ActivityResult> findByGroupId(String groupId) {
        List<ActivityEntity> activityEntityList = activityEntityDao.findByGroupId(groupId);
        return activityEntityList.stream()
                .map(activityEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByGroupId(String activityId, String groupId) {
        activityGroupEntityDao.deleteByActivityIdAndGroupId(activityId, groupId);
    }

    @Override
    public Optional<ActivityResult> findById(String activityId) {
        Optional<ActivityEntity> activityEntity =
                Optional.ofNullable(activityEntityDao.findById(activityId));
        return activityEntity.map(activityEntityMapper::toDomain);
    }

    @Override
    public void upload(UploadActivityOrder uploadActivityOrder) {
        s3Repository.save(uploadActivityOrder);
        activityUserEntityDao.upload(uploadActivityOrder.getActivityId(), uploadActivityOrder.getStudentId(), true);
    }
}
