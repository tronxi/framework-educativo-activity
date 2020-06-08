package es.upm.frameworkeducativoactivity.infrastructure.repository.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentEntityDao {

    @Delete("DELETE FROM ACTIVITY_USER WHERE ID_USER = #{studentId}")
    void deleteById(String studentId);

    @Delete("DELETE FROM ACTIVITY_USER WHERE ID_ACTIVITY IN " +
            "(SELECT ID_ACTIVITY FROM ACTIVITY_GROUP WHERE ID_GROUP = #{groupId}) " +
            "AND ID_USER = #{studentId}")
    void deleteByStudentIdAndGroupId(String studentId, String groupId);
}
