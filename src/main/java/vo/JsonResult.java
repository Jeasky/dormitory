package vo;

public class JsonResult {
    //若登录成功
    private Integer respCode;       //响应码
    private String respMessage;     //响应文本
    private Object data;            //响应数据
    private String errorInfo;       //错误消息

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 是否登录成功
     */
    public static JsonResult isLogin(Object data){

        //产生一个jsonresult对象
        JsonResult jsonResult=new JsonResult();
        jsonResult.setData(data);
        jsonResult.setRespCode(200);
        jsonResult.setRespMessage("登录成功！");

        return jsonResult;
    }

    /**
     * 登录失败
     */
    public static JsonResult isLoginError(String errorInfo){

        JsonResult jsonResult=new JsonResult();
        jsonResult.setRespCode(500);
        jsonResult.setRespMessage("登录失败");
        jsonResult.setErrorInfo(errorInfo);

        return jsonResult;
    }
}
