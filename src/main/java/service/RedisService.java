package service;

import domain.User;
import exception.RedisException;

public interface RedisService {

    /**
     * 向redis缓存中保存用户对象的方法
     * @param user 用户对象
     * @return token 用户对象在缓存中的key
     */
    public String SaveUser(User user);

    /**
     * 用户对象从缓存中取出
     * @param token 用户对象在缓存中的key
     * @return 用户对象
     */
    public User getUser(String token) throws RedisException;
}
