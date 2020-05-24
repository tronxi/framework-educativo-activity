package es.upm.frameworkeducativoactivity.infrastructure.repository.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityGroupEntityDao {

    @Insert("insert into ACTIVITY_GROUP (ID_GROUP, ID_ACTIVITY) VALUES (#{groupId}, #{activityId})")
    void saveActivityGroupList(String activityId, String groupId);

    @Delete("delete from ACTIVITY_GROUP WHERE ID_ACTIVITY = #{activityId} and ID_GROUP = #{groupId}")
    void deleteByGroupId(String activityId, String groupId);
}
