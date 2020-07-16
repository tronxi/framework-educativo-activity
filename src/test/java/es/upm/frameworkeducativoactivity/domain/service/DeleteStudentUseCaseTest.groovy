package es.upm.frameworkeducativoactivity.domain.service

import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteStudent
import es.upm.frameworkeducativoactivity.domain.port.secondary.StudentRepository
import spock.lang.Shared
import spock.lang.Specification

class DeleteStudentUseCaseTest extends Specification {

    @Shared
    StudentRepository studentRepository

    @Shared
    DeleteStudent deleteStudent

    def setup() {
        studentRepository = Mock(StudentRepository)
        deleteStudent = new DeleteStudentUseCase(studentRepository)
    }

    def "should delete student by id"() {
        given:
        String studentId = "id"
        when:
        deleteStudent.deleteStudentById(studentId)
        then:
        1 * studentRepository.deleteById(studentId)
        noExceptionThrown()
    }

    def "should delete student by id and group id"() {
        given:
        String studentId = "id"
        String groupId = "groupId"
        when:
        deleteStudent.deleteStudentByIdAndGroupId(studentId, groupId)
        then:
        1 * studentRepository.deleteByStudentIdAndGroupId(studentId, groupId)
        noExceptionThrown()
    }
}
