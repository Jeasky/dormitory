package mapper;

import domain.Notifycation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotifycationMapper {

    int deleteByPrimaryKey(Integer notifycationid);

    int insert(Notifycation record);

    int insertSelective(Notifycation record);

    Notifycation selectByPrimaryKey(Integer notifycationid);

    List<Notifycation> selectByBuildId(Integer buildid);

    List<Notifycation> selectByKey(@Param(value = "buildid") Integer buildid, @Param(value = "searchKey") String searchKey);

    int updateByPrimaryKeySelective(Notifycation record);

    int updateByPrimaryKey(Notifycation record);
}