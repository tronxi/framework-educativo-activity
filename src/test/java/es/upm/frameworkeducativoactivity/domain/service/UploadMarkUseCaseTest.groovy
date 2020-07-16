package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.model.ActivityResult
import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult
import es.upm.frameworkeducativoactivity.domain.model.UploadMarkOrder
import es.upm.frameworkeducativoactivity.domain.port.primary.UploadMark
import es.upm.frameworkeducativoactivity.domain.port.secondary.ActivityRepository
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository
import spock.lang.Shared
import spock.lang.Specification

class UploadMarkUseCaseTest extends Specification {

    @Shared
    DeliveryRepository deliveryRepository

    @Shared
    ActivityRepository activityRepository

    @Shared
    UploadMark uploadMark

    def setup() {
        deliveryRepository = Mock(DeliveryRepository)
        activityRepository = Mock(ActivityRepository)
        uploadMark = new UploadMarkUseCase(deliveryRepository, activityRepository)
    }

    def "should upload mark when range is ok"() {
        given:
        String studentId = "studentId"
        String activityId = "activityId"
        double mark = 5
        UploadMarkOrder uploadMarkOrder = UploadMarkOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .mark(mark)
                .build()
        ActivityResult activityResult = ActivityResult.builder().build()
        DeliveryResult deliveryResult = DeliveryResult.builder().build()

        activityRepository.findById(uploadMarkOrder.getActivityId()) >> Optional.of(activityResult)
        deliveryRepository
                .findByActivityIdAndStudentId(uploadMarkOrder.getActivityId(),
                        uploadMarkOrder.getStudentId()) >> Optional.of(deliveryResult)
        when:
        uploadMark.upload(uploadMarkOrder)
        then:
        1 * deliveryRepository.uploadMark(uploadMarkOrder)
    }

    def "should throw exception when range is ko"() {
        given:
        String studentId = "studentId"
        String activityId = "activityId"
        double mark = 50
        UploadMarkOrder uploadMarkOrder = UploadMarkOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .mark(mark)
                .build()
        ActivityResult activityResult = ActivityResult.builder().build()
        DeliveryResult deliveryResult = DeliveryResult.builder().build()

        activityRepository.findById(uploadMarkOrder.getActivityId()) >> Optional.of(activityResult)
        deliveryRepository
                .findByActivityIdAndStudentId(uploadMarkOrder.getActivityId(),
                        uploadMarkOrder.getStudentId()) >> Optional.of(deliveryResult)
        when:
        uploadMark.upload(uploadMarkOrder)
        then:
        0 * deliveryRepository.uploadMark(uploadMarkOrder)
        thrown RuntimeException
    }

    def "should throw exception when activity is null"() {
        given:
        String studentId = "studentId"
        String activityId = "activityId"
        double mark = 5
        UploadMarkOrder uploadMarkOrder = UploadMarkOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .mark(mark)
                .build()

        activityRepository.findById(uploadMarkOrder.getActivityId()) >> Optional.empty()

        when:
        uploadMark.upload(uploadMarkOrder)
        then:
        0 * deliveryRepository.uploadMark(uploadMarkOrder)
        thrown RuntimeException
    }

    def "should throw exception when delivery is null"() {
        given:
        String studentId = "studentId"
        String activityId = "activityId"
        double mark = 50
        UploadMarkOrder uploadMarkOrder = UploadMarkOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .mark(mark)
                .build()
        ActivityResult activityResult = ActivityResult.builder().build()

        activityRepository.findById(uploadMarkOrder.getActivityId()) >> Optional.of(activityResult)
        deliveryRepository
                .findByActivityIdAndStudentId(uploadMarkOrder.getActivityId(),
                        uploadMarkOrder.getStudentId()) >> Optional.empty()
        when:
        uploadMark.upload(uploadMarkOrder)
        then:
        0 * deliveryRepository.uploadMark(uploadMarkOrder)
        thrown RuntimeException
    }
}
