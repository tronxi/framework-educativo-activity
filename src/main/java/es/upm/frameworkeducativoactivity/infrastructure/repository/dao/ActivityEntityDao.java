package es.upm.frameworkeducativoactivity.infrastructure.repository.dao;

import es.upm.frameworkeducativoactivity.infrastructure.repository.entity.ActivityEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ActivityEntityDao {

    @Insert("insert into ACTIVITY (ID_ACTIVITY, NAME, MAX_DATE) VALUES " +
            "(#{activityId}, #{name}, #{maxDate})")
    void saveActivity(String activityId, String name, Timestamp maxDate);

    @Select("SELECT ID_ACTIVITY, NAME, MAX_DATE FROM ACTIVITY where ID_ACTIVITY IN" +
            "(SELECT ID_ACTIVITY FROM ACTIVITY_GROUP WHERE ID_GROUP = #{groupId})")
    List<ActivityEntity> findByGroupId(String groupId);

    @Select("SELECT ID_ACTIVITY, NAME, MAX_DATE FROM ACTIVITY where ID_ACTIVITY = #{activityId}")
    ActivityEntity findById(String activityId);
}
