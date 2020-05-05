package service;

import domain.Student;

public interface StudentService {

    public Student selectByOpenid(String openid);

    public int resigt(Student student);
}
