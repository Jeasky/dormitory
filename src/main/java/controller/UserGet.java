package controller;

import domain.Users;
import org.springframework.stereotype.Service;
import service.RedisService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserGet {

    @Resource
    RedisService redisService;

    HttpServletRequest request;

    public Users getUser() throws Exception{
        Users user=new Users();

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
        return user;
    }
}
