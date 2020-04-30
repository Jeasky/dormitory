<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改通知公告</title>
    <%--引入基本路径--%>
    <base href="<%= basePath%>resources/">
    <!-- 字体图标css文件 -->
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1629753_4m2l2q8ewm9.css">
    <!-- 初始化css文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 公共css文件 -->
    <link rel="stylesheet" href="css/conmon.css">
    <!-- 页面css文件 -->
    <link rel="stylesheet" href="css/notifycation.css">
    <!--引入jQuery文件-->
    <script src="js/jquery-3.4.1.min.js"></script>
</head>

<body>
<header>
    <h1> 宿舍管理系统</h1>
    <a href="javascript:;">
            <span class="user">
                <img src="images/user.jpg" alt="">
                <input type="file" name="" id="">
            </span>
        <span class="name">${user.username}</span>
    </a>
</header>
<!-- 头部模块 end -->

<!-- 时间模块 start-->
<div class="time">
    <span>2020年03月21日 20:00:52 星期六</span>
</div>
<!-- 时间模块 end-->

<!-- 侧边模块 start -->
<section>
    <ul>
        <li>
            <a href="/dormitory/notifycation/list?buildid=${user.buildid}">
                <span class="iconfont icon-tongzhigonggao icon1"></span> 通知公告
            </a>
        </li>
        <li>
            <a href="/dormitory/paymentfile/list?buildid=${user.buildid}">
                <span class="iconfont icon-zhangdan  icon"></span> 水电账单
            </a>
        </li>
    </ul>
</section>
<!-- 侧边模块 end -->
<div class="infrom">
        <div class="infrom-hd">
            通知标题:
            <input id="notifycationhead" type="text" value="${notifycation.notifycationhead}">
        </div>

        <div class="infrom-content">
            通知内容:
            <textarea name="notifycationtext" id="notifycationtext" cols="30" rows="10">${notifycation.notifycationtext}</textarea>
        </div>
        <div class="release-unit">
            发布单位:
            <input id="promulgator" type="text" value="${notifycation.promulgator}">
            <a href="/dormitory/notifycation/list?buildid=${user.buildid}" class="cancel">取消</a>
            <a id="publish" class="publish">发布</a>
        </div>
</div>

<script>
    $("#publish").click(function () {
        $.ajax({
            url: "<%= basePath%>notifycation/update",
            type: "POST",
            data: {
                "notifycationid": ${notifycation.notifycationid},
                "buildid": ${user.buildid},
                "notifycationhead": $("#notifycationhead").val(),
                "notifycationtext": $("#notifycationtext").val(),
                "promulgator": $("#promulgator").val()
            },
            dataType: "text",
            success: function (data) {
                alert("修改成功！");
                location.href= "<%=basePath%>notifycation/list?buildid=${user.buildid}";
            }
        })
    });
</script>
</body>

</html>