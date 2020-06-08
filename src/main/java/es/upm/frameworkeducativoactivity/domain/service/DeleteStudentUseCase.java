package es.upm.frameworkeducativoactivity.domain.service;

import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteStudent;
import es.upm.frameworkeducativoactivity.domain.port.secondary.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteStudentUseCase implements DeleteStudent {

    private final StudentRepository studentRepository;

    @Override
    public void deleteStudentById(String studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteStudentByIdAndGroupId(String studentId, String groupId) {
        studentRepository.deleteByStudentIdAndGroupId(studentId, groupId);
    }
}
