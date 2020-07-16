package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteActivity
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository
import spock.lang.Shared
import spock.lang.Specification

class DeleteActivityUseCaseTest extends Specification {

    @Shared
    ActivityRepository activityRepository

    @Shared
    DeleteActivity deleteActivity

    def setup() {
        activityRepository = Mock(ActivityRepository)
        deleteActivity = new DeleteActivityUseCase(activityRepository)
    }

    def "should delete activity by group" () {
        given:
            String activityId = "activityId"
            String groupId = "groupId"
        when:
            deleteActivity.deleteByGroupId(activityId, groupId)
        then:
        1 * activityRepository.deleteByGroupId(activityId, groupId)
        noExceptionThrown()

    }
}
