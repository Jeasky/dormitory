package controller;

import domain.User;
import exception.PasswordException;
import exception.UserNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RedisService;
import service.UserService;
import util.CookieUtils;
import vo.JsonResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController {

    //属性
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;


    @RequestMapping("login")
    @ResponseBody
    public JsonResult login(HttpServletRequest request, HttpServletResponse response, int userid, String passwd, Model model) {
        try {
            //
            User user = this.userService.isLogin(userid,passwd);

            //对user进行缓存处理
            String token=this.redisService.SaveUser(user);

            //拿到一个token后，放入cookie
            CookieUtils.setCookie(request,response,"token",token);

            return JsonResult.isLogin(user);
        } catch (PasswordException e) {
            e.printStackTrace();
            return JsonResult.isLoginError("密码错误!");
        } catch (UserNameException e){
            e.printStackTrace();
            return JsonResult.isLoginError("用户名不存在!");
        }

    }
}
