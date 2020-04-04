package service;

import domain.Users;

public interface UsersService {


    /**
     * 用户登录验证方法
     * @param wechatid 微信id
     * @return 验证成功时返回用户对象
     */
    public Users isLogin(String wechatid);
}
