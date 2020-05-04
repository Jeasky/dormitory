package controller;

import com.github.pagehelper.PageInfo;
import domain.Notifycation;
import domain.Repair;
import domain.User;
import exception.AddException;
import exception.DelException;
import exception.RedisException;
import exception.UpdateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.NotifycationService;
import service.RedisService;
import util.JsonUtils;
import vo.JsonResult;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/notifycation")
public class NotifyController {

    //属性
    @Resource
    NotifycationService notifycationService;

    @Resource
    RedisService redisService;

    //宿管页面展示通知公告
    @RequestMapping("list")
    public ModelAndView search(Integer buildid, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
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
            pageSize = 8;
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
    public ModelAndView preadd(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView=new ModelAndView();

        User user = new User();
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

        modelAndView.addObject("user", user);
        modelAndView.setViewName("notifycation/add");

        return modelAndView;
    }

    //添加通知公告
    @RequestMapping("add")
    public JsonResult add(int buildid, String notifycationhead, String notifycationtext, String promulgator, HttpServletRequest request){
        try {

            Notifycation notifycation = new Notifycation();
            notifycation.setBuildid(buildid);
            notifycation.setNotifycationhead(notifycationhead);
            notifycation.setNotifycationtext(notifycationtext);
            notifycation.setPromulgator(promulgator);
            notifycation.setNotificationdate(new Date());

            //调用业务方法
            int result = this.notifycationService.addNotifycation(notifycation);

            return JsonResult.addOperation(result);
        }catch (AddException e){
            return JsonResult.addError("添加通知公告失败！");
        }
    }

    //删除指定通知公告
    @RequestMapping("delete")
    public ModelAndView delete(int notifycationid,Integer page, Integer pageSize, HttpServletRequest request) throws Exception{

        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
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
            pageSize = 8;
        }

        //调用业务方法
        int result = this.notifycationService.delNotifycation(notifycationid);
        //调用业务方法
        PageInfo<Notifycation> pageInfo = this.notifycationService.displayAll(user.getBuildid(), page, pageSize);

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("notifycation/list");

        return modelAndView;
    }


    //展示通知公告的详细信息
    @RequestMapping("detail")
    public @ResponseBody Notifycation detail(Integer notifycationid, HttpServletRequest request) throws Exception {

        //调用业务方法
        Notifycation notifycation = this.notifycationService.searchById(notifycationid);

        return notifycation;

    }

    //在宿学生展示通知公告列表
    @RequestMapping("display")
    public @ResponseBody PageInfo<Notifycation> display(Integer buildid, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 8;
        }

        //调用业务方法
        PageInfo<Notifycation> pageInfo = this.notifycationService.displayAll(buildid, page, pageSize);

        return pageInfo;

    }

    //在宿学生搜索通知公告列表
    @RequestMapping("search")
    public @ResponseBody PageInfo<Notifycation> search(Integer buildid, String searchKey, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 8;
        }

        PageInfo<Notifycation> pageInfo=null;

        //调用业务方法
        if(searchKey == "") {
            pageInfo = this.notifycationService.displayAll(buildid, page, pageSize);
        }else{
            pageInfo = this.notifycationService.search(buildid,searchKey,page,pageSize);
        }

        return pageInfo;

    }

    //宿管搜索通知公告
    @RequestMapping("find")
    public ModelAndView find(Integer buildid, String searchKey, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
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
            pageSize = 8;
        }

        PageInfo<Notifycation> pageInfo=null;

        //调用业务方法
        if(searchKey == "") {
            pageInfo = this.notifycationService.displayAll(buildid, page, pageSize);
        }else{
            pageInfo = this.notifycationService.search(buildid,searchKey,page,pageSize);
        }

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("notifycation/list");

        return modelAndView;

    }

    //修改通知公告pre
    @RequestMapping("preupdate")
    public ModelAndView preupdate(Integer notifycationid, HttpServletRequest request) throws RedisException {
        ModelAndView modelAndView=new ModelAndView();

        User user = new User();
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

        Notifycation notifycation=this.notifycationService.searchById(notifycationid);

        modelAndView.addObject("notifycation", notifycation);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("notifycation/update");

        return modelAndView;
    }

    //添加通知公告
    @RequestMapping("update")
    public JsonResult update(int notifycationid, int buildid, String notifycationhead, String notifycationtext, String promulgator, HttpServletRequest request){
        try {

            Notifycation notifycation = new Notifycation();
            notifycation.setNotifycationid(notifycationid);
            notifycation.setBuildid(buildid);
            notifycation.setNotifycationhead(notifycationhead);
            notifycation.setNotifycationtext(notifycationtext);
            notifycation.setPromulgator(promulgator);
            notifycation.setNotificationdate(new Date());

            //调用业务方法
            int result = this.notifycationService.updateNotifycation(notifycation);

            System.out.println("修改结果："+result);

            return JsonResult.addOperation(result);
        }catch (UpdateException e){
            return JsonResult.addError("修改通知公告失败！");
        }
    }

}
