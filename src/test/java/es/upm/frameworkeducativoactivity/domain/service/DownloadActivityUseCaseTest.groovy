package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityOrder
import es.upm.frameworkeducativoactivity.domain.model.DownloadActivityResult
import es.upm.frameworkeducativoactivity.domain.port.primary.DownloadActivity
import es.upm.frameworkeducativoactivity.domain.port.secondary.FileRepository
import spock.lang.Shared
import spock.lang.Specification

class DownloadActivityUseCaseTest extends Specification {

    @Shared
    FileRepository fileRepository

    @Shared
    DownloadActivity downloadActivity

    def setup() {
        fileRepository = Mock(FileRepository)
        downloadActivity = new DownloadActivityUseCase(fileRepository)
    }

    def "show download file"() {
        given:
        String activityId = "activityId"
        String studentId = "studentId"
        String name = "name"

        DownloadActivityOrder downloadActivityOrder = DownloadActivityOrder.builder()
                .studentId(studentId)
                .activityId(activityId)
                .build()
        DownloadActivityResult expect = DownloadActivityResult.builder()
                .name(name)
                .build()

        fileRepository.get(downloadActivityOrder) >> expect
        when:
        DownloadActivityResult downloadActivityResult = downloadActivity.download(downloadActivityOrder)
        then:
        downloadActivityResult == expect
    }
}
