package exception;

public class DelException extends Exception {
    //自定义删除操作失败异常
    public DelException(String message){
        super(message);
    }
}
