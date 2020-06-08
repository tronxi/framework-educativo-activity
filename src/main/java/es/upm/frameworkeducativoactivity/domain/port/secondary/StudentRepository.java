package es.upm.frameworkeducativoactivity.domain.port.secondary;


public interface StudentRepository {

    void deleteById(String studentId);
    void deleteByStudentIdAndGroupId(String studentId, String groupId);
}
