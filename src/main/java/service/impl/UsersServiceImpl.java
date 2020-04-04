package service.impl;

import domain.Users;
import mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import service.UsersService;

import javax.annotation.Resource;

@Service("UsersServiceImpl")
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Users isLogin(String wechatid) {
        Users users=new Users();

        users=usersMapper.loginById(wechatid);

        if(users != null){
            return users;
        }else
            return null;
    }
}
