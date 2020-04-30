package service.impl;

import domain.User;
import jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.RedisService;
import util.JsonUtils;

import java.util.UUID;

@Service("RedisServiceImpl")
public class RedisServiceImpl implements RedisService {

    @Autowired@Qualifier("JedisClientImpl")
    private JedisClient jedisClient;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public String SaveUser(User users) {

        //清空你的敏感数据
//        users.setUserPass(null);

        //将用户对象转回json
        String json= JsonUtils.objectToJson(users);

        //产生一个id
        String token=UUID.randomUUID().toString();

        //保存用户到redis
        this.jedisClient.set(token,json);

        //设置一个有效时间
        jedisClient.expire(token,5*60);

        return token;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public User getUser(String token) {

        //获取用户对象
        String json=this.jedisClient.get(token);

        //将json数据转换成实体对象，先判断一下json数据是否为空
        if(StringUtils.isNotBlank(json)){

            //redis设置时间，每操作一次，重置一次时长
            jedisClient.expire(token,30*60);

            return JsonUtils.jsonToPojo(json, User.class);
        }else {
            //抛异常
            return null;
        }
    }
}
