package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.model.DeliveryResult
import es.upm.frameworkeducativoactivity.domain.port.primary.FindDeliveryGroup
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository
import spock.lang.Shared
import spock.lang.Specification

class FindDeliveryGroupUseCaseTest extends Specification {

    @Shared
    DeliveryRepository deliveryRepository

    @Shared
    FindDeliveryGroup findDeliveryGroup

    def setup() {
        deliveryRepository = Mock(DeliveryRepository)
        findDeliveryGroup = new FindDeliveryGroupUseCase(deliveryRepository)
    }

    def "should return deliveryResultList by id"() {
        given:
        String activityId = "activityId"
        DeliveryResult deliveryResult = DeliveryResult.builder()
                .activityId(activityId)
                .build()
        List<DeliveryResult> expected = Arrays.asList(deliveryResult)

        deliveryRepository.findByActivityId(activityId) >> expected
        when:
        List<DeliveryResult> deliveryResultList = findDeliveryGroup.findById(activityId)
        then:
        expected == deliveryResultList
    }
}
