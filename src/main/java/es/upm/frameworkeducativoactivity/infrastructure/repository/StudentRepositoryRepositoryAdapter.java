package es.upm.frameworkeducativoactivity.infrastructure.repository;

import es.upm.frameworkeducativoactivity.domain.port.secondary.StudentRepository;
import es.upm.frameworkeducativoactivity.infrastructure.repository.dao.StudentEntityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRepositoryRepositoryAdapter implements StudentRepository {

    private final StudentEntityDao studentEntityDao;

    @Override
    public void deleteById(String studentId) {
        studentEntityDao.deleteById(studentId);
    }

    @Override
    public void deleteByStudentIdAndGroupId(String studentId, String groupId) {
        studentEntityDao.deleteByStudentIdAndGroupId(studentId, groupId);
    }
}
