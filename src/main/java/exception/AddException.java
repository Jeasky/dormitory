package exception;

public class AddException extends Exception {
    //自定义添加操作失败异常
    public AddException(String message){
        super(message);
    }
}
