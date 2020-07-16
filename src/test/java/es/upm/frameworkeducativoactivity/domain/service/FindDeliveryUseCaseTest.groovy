package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.model.DeliveryOrder
import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository
import spock.lang.Shared
import spock.lang.Specification

class FindDeliveryUseCaseTest extends Specification {

    @Shared
    DeliveryRepository deliveryRepository

    @Shared
    FindDeliveryUseCase findDeliveryUseCase

    def setup() {
        deliveryRepository = Mock(DeliveryRepository)
        findDeliveryUseCase = new FindDeliveryUseCase(deliveryRepository)
    }

    def "find Ok by id"() {
        given:
        String studentId = "studentId"
        String activityId = "activityId"
        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .build()

        DeliveryResult expected = DeliveryResult.builder()
                .activityId(activityId)
                .userId(studentId)
                .build()

        deliveryRepository
                .findByActivityIdAndStudentId(deliveryOrder.getActivityId(),
                        deliveryOrder.getStudentId()) >> Optional.of(expected)
        when:
        DeliveryResult deliveryResult = findDeliveryUseCase.findById(deliveryOrder)
        then:
        deliveryResult == expected
    }

    def "find KO by id"() {
        given:
        String studentId = "studentId"
        String activityId = "activityId"
        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .activityId(activityId)
                .studentId(studentId)
                .build()

        DeliveryResult expectedFailed = DeliveryResult.builder()
                .activityId(activityId)
                .userId(studentId)
                .mark(0)
                .finished(false)
                .build()

        deliveryRepository
                .findByActivityIdAndStudentId(deliveryOrder.getActivityId(),
                        deliveryOrder.getStudentId()) >> Optional.empty()
        when:
        DeliveryResult deliveryResult = findDeliveryUseCase.findById(deliveryOrder)
        then:
        deliveryResult == expectedFailed
    }
}
