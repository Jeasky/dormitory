package mapper;

import domain.Notifycation;

import java.util.List;

public interface NotifycationMapper {

    int deleteByPrimaryKey(Integer notifycationid);

    int insert(Notifycation record);

    int insertSelective(Notifycation record);

    Notifycation selectByPrimaryKey(Integer notifycationid);

    List<Notifycation> selectByBuildId(Integer buildid);

    int updateByPrimaryKeySelective(Notifycation record);

    int updateByPrimaryKey(Notifycation record);
}