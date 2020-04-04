package controller;

import com.github.pagehelper.PageInfo;
import domain.Notifycation;
import domain.Repair;
import domain.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.NotifycationService;
import service.RedisService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/notifycation")
public class NotifyController {

    //属性
    @Resource
    NotifycationService notifycationService;

    @Resource
    RedisService redisService;

    @RequestMapping("list")
    public ModelAndView search(Integer buildid, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        Users user = new Users();
        //获取Cookie
        Cookie[] cookies = request.getCookies();

        //对cookies判断
        if (cookies != null) {

            //遍历
            for (Cookie cookie : cookies) {

                //获取cookie的键
                String name = cookie.getName();

                //对name进行判断，name是否就是我们需要的token
                if ("token".equals(name)) {

                    //从把cookies中name对应的值取出来
                    //这个值就是token
                    String token = cookie.getValue();     //这个tokne还是redis的key

                    //根据这个token获取到user
                    //从缓存中取出这个数据
                    user = this.redisService.getUser(token);
                }
            }
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 3;
        }

        //调用业务方法
        PageInfo<Notifycation> pageInfo = this.notifycationService.displayAll(buildid, page, pageSize);

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("notifycation/list");

        return modelAndView;

    }

    @RequestMapping("preadd")
    public ModelAndView preadd(Users user, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView=new ModelAndView();

        modelAndView.addObject("user", user);
        modelAndView.setViewName("notifycation/add");

        return modelAndView;
    }

    @RequestMapping("add")
    public ModelAndView add(Users user,Integer page, Integer pageSize, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView=new ModelAndView();

        Notifycation notifycation=new Notifycation();
        notifycation.setBuildid(user.getBuildid());
        HttpSession session= request.getSession();
        notifycation.setNotifycationhead((String) session.getAttribute("notifycationhead"));
        notifycation.setNotifycationtext((String) session.getAttribute("notifycationtext"));
        notifycation.setPromulgator((String) session.getAttribute("promulgator"));
        notifycation.setNotificationdate(new Date());

        //调用业务方法
        int result=this.notifycationService.addNotifycation(notifycation);
        PageInfo<Notifycation> pageInfo = this.notifycationService.displayAll(user.getBuildid(), page, pageSize);

        modelAndView.addObject("result", result);
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("notifycation/list");

        return modelAndView;
    }

}
