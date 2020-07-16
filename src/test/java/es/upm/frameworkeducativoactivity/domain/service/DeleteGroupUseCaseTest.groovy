package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteGroup
import es.upm.frameworkeducativoactivity.domain.port.secondary.DeliveryRepository
import spock.lang.Shared
import spock.lang.Specification

class DeleteGroupUseCaseTest extends Specification {

    @Shared
    DeliveryRepository deliveryRepository

    @Shared
    DeleteGroup deleteGroup

    def setup() {
        deliveryRepository = Mock(DeliveryRepository)
        deleteGroup = new DeleteGroupUseCase(deliveryRepository)
    }

    def "should delete by group id"() {
        given:
            String groupId = "id"
        when:
            deleteGroup.deleteGroupById(groupId)
        then:
            1 * deliveryRepository.deleteByGroupId(groupId)
            noExceptionThrown()
    }
}
