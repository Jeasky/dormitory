package exception;

/**
 * 用户名自定义异常类
 */
public class UserNameException extends Exception {

    public UserNameException(String message){
        super(message);
    }
}
