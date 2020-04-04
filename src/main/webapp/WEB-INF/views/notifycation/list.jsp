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
    <title>通知公告页面</title>
    <%--引入基本路径--%>
    <base href="<%=basePath%>resources/">
    <!-- 字体图标css文件 -->
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1629753_4n5ive5fsq.css">
    <!-- 初始化css文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 引入公共css文件 -->
    <link rel="stylesheet" href="css/conmon.css">
    <!-- 引入页面css文件 -->
    <link rel="stylesheet" href="css/manage.css">
</head>

<body>
<!-- 头部模块 start-->
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

<div class="list">
    <h2>
        通知列表
        <div class="input">
            <input id="inputheadline" type="text" value="请输入通知标题">
        </div>
        <div class="add">
            添加通知公告
            <a href="/dormitory/notifycation/preadd?user=${user}" class="iconfont icon-add-fill icon"></a>
        </div>
    </h2>

    <table border="1" cellspacing="0">
        <thead>
        <tr>
            <td>通知标题</td>
            <td class="content">通知内容</td>
            <td>发布单位</td>
            <td>发布时间</td>
            <td>宿舍楼</td>
            <td>操作</td>
        </tr>
        </thead>
        <c:forEach items="${pageInfo.list}" var="notifycation">
            <tr>
                <td>${notifycation.notifycationhead}</td>
                <td>${notifycation.notifycationtext}</td>
                <td>${notifycation.promulgator}</td>
                <td>${notifycation.notificationdate}</td>
                <td>${notifycation.buildid}</td>
                <td>
                    <a href="/dormitory/notifycation/update?notifycationid=${notifycation.notifycationid}" class="iconfont icon-bi"></a>
                    <a href="/dormitory/notifycation/delete?notifycationid=${notifycation.notifycationid}" class="iconfont icon-lajitong"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="list-bd">
        <!-- 上一页 -->
        <!-- 做一个判断，是否为第一页 -->
        <c:choose>
            <c:when test="${pageInfo.pageNum eq 1}">
                <a>上一页</a>
            </c:when>
            <c:otherwise>
                <a href="/dormitory/notifycation/list?page=${pageInfo.pageNum-1}" target="_self">上一页</a>
            </c:otherwise>
        </c:choose>
        <!-- 此处引入当前页面信息 -->
        <span>当前第${pageInfo.pageNum}页 共${pageInfo.pages}页</span>
        <!-- 下一页 -->
        <!-- 做一个判断，是否为最后一页 -->
        <c:choose>
            <c:when test="${pageInfo.pageNum eq pageInfo.pages}">
                <a>下一页</a>
            </c:when>
            <c:otherwise>
                <a href="/dormitory/notifycation/list?page=${pageInfo.pageNum+1}" target="_self">下一页</a>
            </c:otherwise>
        </c:choose>向第 <input id="inputpage" type="text"> 页
        <a class="skip" onclick="jump()">跳转</a>
        <script type="text/javascript">
            function jump() {
                window.location.href = "/dormitory/notifycation/list?page=" + $("#inputpage").val();
            }
        </script>
    </div>
</div>
<!-- 展示列表模块 end -->
</body>

</html>
