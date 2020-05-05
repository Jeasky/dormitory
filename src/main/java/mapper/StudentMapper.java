package mapper;

import domain.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentid);

    Student selectByOpenID(String openid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}