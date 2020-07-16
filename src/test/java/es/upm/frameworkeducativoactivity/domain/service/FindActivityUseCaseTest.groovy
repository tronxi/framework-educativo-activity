package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult
import es.upm.frameworkeducativoactivity.domain.port.primary.FindActivity
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository
import spock.lang.Shared
import spock.lang.Specification

class FindActivityUseCaseTest extends Specification {

    @Shared
    ActivityRepository activityRepository

    @Shared
    FindActivity findActivity

    def setup() {
        activityRepository = Mock(ActivityRepository)
        findActivity = new FindActivityUseCase(activityRepository)
    }

    def "should find by group id"() {
        given:
        String groupId = "groupId"
        String name = "name"
        String activityId = "activityId"
        ActivityResult activityResult = ActivityResult.builder()
                .name(name)
                .activityId(activityId)
                .build()
        List<ActivityResult> expected = Arrays.asList(activityResult)
        activityRepository.findByGroupId(groupId) >> expected
        when:
        List<ActivityResult> activityResultList = findActivity.findByGroupId(groupId)
        then:
        activityResultList == expected
    }
}
