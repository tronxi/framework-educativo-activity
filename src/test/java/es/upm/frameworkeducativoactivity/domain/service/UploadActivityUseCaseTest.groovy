package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult
import es.upm.frameworkeducativoactivity.domain.model.UploadActivityOrder
import es.upm.frameworkeducativoactivity.domain.model.UploadActivityResult
import es.upm.frameworkeducativoactivity.domain.port.primary.UploadActivity
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository
import spock.lang.Shared
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class UploadActivityUseCaseTest extends Specification {
    @Shared
    ActivityRepository activityRepository

    @Shared
    UploadActivity uploadActivity

    def setup() {
        activityRepository = Mock(ActivityRepository)
        uploadActivity = new UploadActivityUseCase(activityRepository)
    }

    def "should upload activity when date is ok"() {
        given:
        String name = "name"
        String studentId = "studentId"
        String activityId = "activityId"
        UploadActivityOrder uploadActivityOrder = UploadActivityOrder.builder()
                .name(name)
                .studentId(studentId)
                .activityId(activityId)
                .build()
        UploadActivityResult expected = UploadActivityResult.builder()
                .done(true)
                .build()
        ActivityResult activityResult = ActivityResult.builder()
                .activityId(activityId)
                .name(name)
                .maxDate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)))
                .build()
        activityRepository.findById(uploadActivityOrder.getActivityId()) >> Optional.of(activityResult)
        when:
        UploadActivityResult uploadActivityResult = uploadActivity.uploadActivity(uploadActivityOrder)
        then:
        1 * activityRepository.upload(uploadActivityOrder)
        uploadActivityResult == expected
    }

    def "should return ko when date is ko"() {
        given:
        String name = "name"
        String studentId = "studentId"
        String activityId = "activityId"
        UploadActivityOrder uploadActivityOrder = UploadActivityOrder.builder()
                .name(name)
                .studentId(studentId)
                .activityId(activityId)
                .build()
        UploadActivityResult expected = UploadActivityResult.builder()
                .done(false)
                .build()
        ActivityResult activityResult = ActivityResult.builder()
                .activityId(activityId)
                .name(name)
                .maxDate(Timestamp.valueOf(LocalDateTime.now().plusDays(-1)))
                .build()
        activityRepository.findById(uploadActivityOrder.getActivityId()) >> Optional.of(activityResult)
        when:
        UploadActivityResult uploadActivityResult = uploadActivity.uploadActivity(uploadActivityOrder)
        then:
        0 * activityRepository.upload(uploadActivityOrder)
        uploadActivityResult == expected
    }

    def "should throw exception when activity is null"() {
        given:
        String name = "name"
        String studentId = "studentId"
        String activityId = "activityId"
        UploadActivityOrder uploadActivityOrder = UploadActivityOrder.builder()
                .name(name)
                .studentId(studentId)
                .activityId(activityId)
                .build()
        activityRepository.findById(uploadActivityOrder.getActivityId()) >> Optional.empty()
        when:
        uploadActivity.uploadActivity(uploadActivityOrder)
        then:
        0 * activityRepository.upload(uploadActivityOrder)
        thrown RuntimeException
    }
}
