package mapper;

import domain.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    int deleteByPrimaryKey(Integer messageid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageid);

    List<Message> selectByRoom(@Param("buildid") Integer buildid, @Param("roomid") Integer roomid);

    List<Message> selectByKey(@Param("buildid") Integer buildid, @Param("roomid") Integer roomid, @Param("searchKey") String searchKey);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}