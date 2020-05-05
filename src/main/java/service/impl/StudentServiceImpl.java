package service.impl;

import domain.Student;
import mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.StudentService;

@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Student selectByOpenid(String openid){

        Student student=this.studentMapper.selectByOpenID(openid);

        return student;
    }
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int resigt(Student student){

        int result = this.studentMapper.insert(student);

        return result;
    }
}
