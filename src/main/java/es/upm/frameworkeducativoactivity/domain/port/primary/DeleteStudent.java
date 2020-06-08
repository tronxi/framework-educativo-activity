package es.upm.frameworkeducativoactivity.domain.port.primary;

public interface DeleteStudent {
    void deleteStudentById(String studentId);
    void deleteStudentByIdAndGroupId(String studentId, String groupId);
}
