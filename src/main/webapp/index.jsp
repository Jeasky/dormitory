<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <base href="<%=basePath%>resources/">
    <!-- 初始化css文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 页面css文件 -->
    <link rel="stylesheet" href="css/login.css">
    <!--引入jQuery文件-->
    <script src="js/jquery-3.4.1.min.js"></script>
</head>

<body>
<div class="login">
    <h1>宿舍管理系统</h1>
    <form action="">
        <label for="wechatid">用户名:</label>
        <input type="text" id="wechatid" value="请输入用户名ID">
        <br>
        <label for="pwd">密码:</label>
        <input type="text" id="pwd" value="请输入密码">
        <br>
        <a id="loginbtn" class="login-btn">登录</a>
        <a id="reset">重置</a>
    </form>
</div>
</body>


<script>
    $("#loginbtn").click(function () {
        $.ajax({
            url: "<%=basePath%>user/login",
            type: "POST",
            data: {
                "wechatid": $("#wechatid").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.respCode == 200) {
                    //根据用户类型进行跳转
                    //用户类型字段为1,为学生用户,2为宿舍管理人员,3为维修人员
                    switch (data.data.usertype) {
                        case 1: location.href = "<%=basePath%>stu/notifycation"; break;
                        case 2: location.href = "<%=basePath%>notifycation/list?buildid="+data.data.buildid; break;
                        case 3: location.href = "<%=basePath%>repair/search"; break;
                        default : location.href = "<%=basePath%>"; break;
                    }

                } else {
                    alert(data.respMessage + ":" + data.errorInfo);
                }
            }
        })
    });

    $("#wechatid").onfocus(function () {
        $("#wechatid").val("");
    });

    $("#pwd").onfocus(function () {
        $("#pwd").val("");
    });

    $("#reset").click(function () {
        $("#wechatid").val("");
        $("#pwd").val("");
    })
</script>
</html>