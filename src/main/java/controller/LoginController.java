package controller;

import domain.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UsersService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/index")
public class LoginController {

    //属性
    @Resource
    private UsersService usersService;

    /**
     * 控制方法，实现登录功能
     * model 相当于request对象，可以保存数据对象
     */
    @RequestMapping("login")
    public String login(String wechatid, Model model) {

        Users users=usersService.isLogin(wechatid);

        if(users != null){

            //把用户数据信息放入到容器中,以后能够从jsp页面中取出来
            model.addAttribute("user", users);

            switch (users.getUsertype()){
                case 1: return "student";
                case 2: return "manager";
                case 3: return "repair";
                default: return "usertypeerror";
            }
        }else return "index";
    }

}
