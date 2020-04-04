package mapper;

import domain.Users;

public interface UsersMapper {

    Users loginById(String wechatid);

    int insert(Users record);

    int insertSelective(Users record);
}