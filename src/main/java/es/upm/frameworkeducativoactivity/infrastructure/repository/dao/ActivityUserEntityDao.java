package es.upm.frameworkeducativoactivity.infrastructure.repository.dao;

import es.upm.frameworkeducativoactivity.infrastructure.repository.entity.ActivityUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ActivityUserEntityDao {

    @Insert("insert into ACTIVITY_USER (ID_ACTIVITY, ID_USER, FINISHED) VALUES (#{activityId}, #{userId}, #{finished})")
    void upload(String activityId, String userId, boolean finished);

    @Select("select ID_ACTIVITY, ID_USER, MARK, FINISHED FROM ACTIVITY_USER WHERE ID_USER = #{studentId} AND ID_ACTIVITY = #{activityId}")
    ActivityUserEntity findByStudentIdAndActivityId(String studentId, String activityId);

    @Update("update ACTIVITY_USER SET MARK = #{mark} where ID_ACTIVITY = #{activityId} and ID_USER = #{userId}")
    void uploadMark(double mark, String activityId, String userId);
}
