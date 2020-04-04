package mapper;

import domain.Message;

import java.util.List;

public interface MessageMapper {

    int deleteByPrimaryKey(Integer messageid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageid);

    List<Message> selectByRoom(Integer buildid, Integer roomid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}