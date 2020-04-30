package exception;

public class UpdateException extends Exception {
    //自定义修改操作失败异常
    public UpdateException(String message){
        super(message);
    }
}
