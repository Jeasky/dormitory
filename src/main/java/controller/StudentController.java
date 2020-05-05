package controller;


import domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StudentService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("login")
    public @ResponseBody Student login(String openid) throws Exception{

        Student student=this.studentService.selectByOpenid(openid);

        return student;
    }

    @RequestMapping("resigt")
    public @ResponseBody int resigt(Integer studentid, String openid, String studentname, Integer buildid, Integer roomid) throws Exception{

        Student student=new Student();
        student.setStudentid(studentid);
        student.setOpenid(openid);
        student.setStudentname(studentname);
        student.setBuildid(buildid);
        student.setRoomid(roomid);

        int result=this.studentService.resigt(student);

        return result;
    }
}
