package exception;

/**
 * 用户密码自定义异常类
 */
public class PasswordException extends Exception{

    public PasswordException(String message){
        super(message);
    }
}
