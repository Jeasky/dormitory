<%--
  Created by IntelliJ IDEA.
  User: oyjj6
  Date: 2020/3/24
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加通知公告</title>
    <base href="<%= basePath%>/resources/">
    <!-- 字体图标css文件 -->
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1629753_4m2l2q8ewm9.css">
    <!-- 初始化css文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 公共css文件 -->
    <link rel="stylesheet" href="css/conmon.css">
    <!-- 页面css文件 -->
    <link rel="stylesheet" href="css/notifycation.css">
</head>

<body>
<header>
    <h1> 宿舍管理系统</h1>
    <a href="javascript:;">
            <span class="user">
                <img src="images/user.jpg" alt="">
                <input type="file" name="file" id="file">
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
            <a href="/dormitory/notifycation/list?buildid="${user.buildid}>
                <span class="iconfont icon-tongzhigonggao icon1"></span> 通知公告
            </a>
        </li>
        <li>
            <a href="/dormitory/payment/list">
                <span class="iconfont icon-zhangdan  icon"></span> 水电账单
            </a>
        </li>
    </ul>
</section>
<!-- 侧边模块 end -->
<div class="infrom">
    <form action="notifycation/add" method="post">
        <div class="infrom-hd">
            通知标题:
            <input id="notifycationhead" type="text" value="请输入通知公告标题">
        </div>

        <div class="infrom-content">
            通知内容:
            <textarea name="notifycationtext" id="notifycationtext" cols="30" rows="10" placeholder="请输入通知内容"></textarea>
        </div>
        <div class="release-unit">
            发布单位:
            <input id="promulgator" type="text" value="请输入发布单位">
            <a href="/dormitory/notifycation/list?buildid=${user.buildid}" class="cancel">取消</a>
            <%--<a href="/dormitory/notifycation/add" class="publish">发布</a>--%>
            <a type="submit" class="publish">发布</a>
        </div>
    </form>
</div>
</body>

</html>
