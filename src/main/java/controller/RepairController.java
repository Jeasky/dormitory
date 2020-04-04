package controller;


import com.github.pagehelper.PageInfo;
import domain.Repair;
import domain.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.RedisService;
import service.RepairService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/repair")
public class RepairController {

    //属性
    @Resource
    RepairService repairService;

    @Resource
    RedisService redisService;

    @RequestMapping("search")
    public ModelAndView search(Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

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
        PageInfo<Repair> pageInfo = this.repairService.displayAll(page, pageSize);


        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("repair/list");

        return modelAndView;
    }

    @RequestMapping("done")
    public ModelAndView done(Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

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
        PageInfo<Repair> pageInfo = this.repairService.displayDone(page, pageSize);


        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("repair/done");

        return modelAndView;
    }

    @RequestMapping("undo")
    public ModelAndView undo(Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

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
        PageInfo<Repair> pageInfo = this.repairService.displayUndo(page, pageSize);


        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("repair/undo");

        return modelAndView;
    }

    @RequestMapping("displayroom")
    public ModelAndView displayroom(Integer page, Integer pageSize, HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        //获取当前用户信息
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
        PageInfo<Repair> pageInfo = this.repairService.displayRoom(page, pageSize, user.getBuildid(), user.getRoomid());

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("repair/list");

        return modelAndView;
    }

    @RequestMapping("changestatus")
    public ModelAndView changestatus(Integer repairid, Integer repairstatus, String repairperson, String pagename, Integer page, Integer pageSize, HttpServletRequest request) throws Exception {
        Date repairdateend=new Date();

        ModelAndView modelAndView = new ModelAndView();

        //获取当前用户信息
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

        PageInfo<Repair> pageInfo;

        boolean result = this.repairService.changeStatus(repairid, repairstatus, repairperson, repairdateend);

        //调用业务方法
        if (pagename.equals("list"))
            pageInfo = this.repairService.displayAll(page, pageSize);
        else
            pageInfo = this.repairService.displayUndo(page, pageSize);

        //把数据放入到容器中，以后能够从jsp页面中从这个容器中取出
        modelAndView.addObject("result", result);
        modelAndView.addObject("user", user);
        modelAndView.addObject("pageInfo", pageInfo);

        //view 设置返回地址
        modelAndView.setViewName("repair/" + pagename);

        return modelAndView;
    }
}
