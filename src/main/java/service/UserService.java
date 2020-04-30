package service;

import domain.User;
import exception.PasswordException;
import exception.UserNameException;

public interface UserService {

    public User isLogin(int userid, String passwd)  throws UserNameException, PasswordException;
}
