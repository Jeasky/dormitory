package controller;

import domain.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RedisService;
import service.UsersService;
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
    private UsersService usersService;
    @Resource
    private RedisService redisService;

    /**
     * 控制方法，实现登录功能
     * model 相当于request对象，可以保存数据对象
     */
    @RequestMapping("login")
    @ResponseBody
    public JsonResult login(HttpServletRequest request, HttpServletResponse response, String wechatid, Model model) {
        //业务调用
        Users users = this.usersService.isLogin(wechatid);

        //对user进行缓存处理
        String token = this.redisService.SaveUser(users);
        //拿到一个token后,放入到cookie中
        CookieUtils.setCookie(request, response, "token", token);

        model.addAttribute("user", users);

        return JsonResult.isLogin(users);

    }
}
