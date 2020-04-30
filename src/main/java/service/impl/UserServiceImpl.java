package service.impl;

import domain.User;
import exception.PasswordException;
import exception.UserNameException;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public User isLogin(int userid, String passwd) throws UserNameException, PasswordException {

        User user = this.userMapper.selectByPrimaryKey(userid);

        if(user==null){
            //用户名不存在
            throw new UserNameException("用户名不存在！");
        }
        //判断密码的正确性
        if (passwd.equals(user.getPasswd())){
            return user;
        }
        throw new PasswordException("密码错误！");
    }
}
