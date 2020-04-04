package interceptor;

import domain.Users;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import service.RedisService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisService redisService;

    /**
     * 创建登录拦截器
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("这是一个拦截器！");

        //下面是拦截器的功能
        //得到cookie
        Cookie[] cookies=request.getCookies();

        //对cookies判断
        if(cookies != null){

            //遍历
            for (Cookie cookie: cookies){

                //获取cookie的键
                String name=cookie.getName();

                //对name进行判断，name是否就是我们需要的token
                if("token".equals(name)){

                    //从把cookies中name对应的值取出来
                    //这个值就是token
                    String token=cookie.getValue();     //这个tokne还是redis的key

                    //根据这个token获取到user
                    //从缓存中取出这个数据
                    Users user=this.redisService.getUser(token);

                    //保存到本地session
                    HttpSession session=request.getSession();

                    //保存session
                    session.setAttribute("user",user);

                    //返回
                    return true;
                }
            }
        }

        //在返回false前，把用户引导到登录页面
        response.sendRedirect("http://localhost:8080/dormitory/index.jsp");

        return false;
    }
}
