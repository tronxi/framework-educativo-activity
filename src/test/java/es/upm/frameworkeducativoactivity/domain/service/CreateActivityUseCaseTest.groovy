package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.model.CreateActivityOrder
import es.upm.frameworkeducativoactivity.domain.model.CreateActivityResult
import es.upm.frameworkeducativoactivity.domain.port.primary.CreateActivity
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository
import spock.lang.Shared
import spock.lang.Specification

import java.sql.Timestamp
import java.time.Instant

class CreateActivityUseCaseTest extends Specification {

    @Shared
    ActivityRepository activityRepository

    @Shared
    CreateActivity createActivityUseCase

    def setup() {
        activityRepository = Mock(ActivityRepository)
        createActivityUseCase = new CreateActivityUseCase(activityRepository)
    }

    def "should create activity"() {
        given:
        String name = "name"
        Timestamp time = Timestamp.from(Instant.now())
        String activityId = "id"

        CreateActivityOrder createActivityOrder = CreateActivityOrder.builder()
                .name(name)
                .maxDate(time)
                .build()

        CreateActivityResult expect = CreateActivityResult.builder()
                .activityId(activityId)
                .build()

        activityRepository.create(createActivityOrder) >> expect

        when:
        CreateActivityResult createActivityResult = createActivityUseCase.createActivity(createActivityOrder)
        then:
        expect == createActivityResult
    }
}
