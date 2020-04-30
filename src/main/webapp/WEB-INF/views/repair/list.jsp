<%@ page import="domain.User" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <base href="<%=basePath%>resources/">
    <!-- 初始化css文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 字体图标css引入 -->
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1629753_7hr0uvvlgwg.css">
    <!-- 页面css文件 -->
    <link rel="stylesheet" href="css/repair.css">
    <!--引入jQuery文件-->
    <script src="js/jquery-3.4.1.min.js"></script>
    <!-- 引入时间js文件 -->
    <script src="js/time.js"></script>
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
    <span>登录时间</span><span id="time">2018年1月1日 11:11 星期一</span>
</div>
<!-- 时间模块 end-->

<!-- 侧边模块 start -->
<section>
    <ul>
        <li><a href="/dormitory/repair/search"><span class="iconfont icon-quanbu icon1"></span> 全部</a></li>
        <li><a href="/dormitory/repair/undo"><span class="iconfont icon-weichuli icon2"></span> 未处理</a></li>
        <li><a href="/dormitory/repair/done"><span class="iconfont icon-yichuli  icon3"></span> 已处理</a></li>
    </ul>
</section>
<!-- 侧边模块 end -->

<!-- 展示列表模块 start -->
<div class="list">
    <h2>展示列表</h2>
    <table border="1" cellspacing="0">
        <thead>
        <tr>
            <td>维修设备ID</td>
            <td>维修物品</td>
            <td>维修物品描述</td>
            <td>报备宿舍</td>
            <td>报备时间</td>
            <td>维修状态</td>
            <td>维修人员</td>
            <td>维修时间</td>
            <td>完成维修</td>
        </tr>
        </thead>
        <c:forEach items="${pageInfo.list}" var="repair">
            <tr>
                <td>${repair.repairid}</td>
                <td>${repair.repairthing}</td>
                <td>${repair.repairdes}</td>
                <td>${repair.buildid}-${repair.roomid}</td>
                <td id="repairdatefrom">${repair.repairdatefrom}</td>
                <c:choose>
                    <c:when test="${repair.repairstatus eq 0}">
                        <td>未维修</td>
                    </c:when>
                    <c:otherwise>
                        <td>已维修</td>
                    </c:otherwise>
                </c:choose>
                <td>${repair.repairperson}</td>
                <td>${repair.repairdateend}</td>
                <c:choose>
                    <c:when test="${repair.repairstatus eq 0}">
                        <td><a id="changestatus" class="iconfont icon-yichuli1"
                               href="<%=basePath%>repair/changestatus?repairid=${repair.repairid}&repairstatus=1&repairperson=${user.username}&pagename=list&page=${pageInfo.pageNum}"></a>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td><a id="cantchange" class="iconfont icon-yichuli  icon"></a></td>
                    </c:otherwise>
                </c:choose>
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
                <a href="/dormitory/repair/search?page=${pageInfo.pageNum-1}" target="_self">上一页</a>
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
                <a href="/dormitory/repair/search?page=${pageInfo.pageNum+1}" target="_self">下一页</a>
            </c:otherwise>
        </c:choose>向第 <input id="inputpage" type="text"> 页
        <a class="skip" onclick="jump()">跳转</a>
        <script type="text/javascript">
            function jump() {
                window.location.href = "/dormitory/repair/search?page=" + $("#inputpage").val();
            }
        </script>
    </div>
</div>
<!-- 展示列表模块 end -->

</body>

</html>

