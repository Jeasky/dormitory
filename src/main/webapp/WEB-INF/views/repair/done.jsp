<%@ page import="domain.User" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--使用fmt标签，格式化时间日期--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <span id="nowTime"></span>
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
        </tr>
        </thead>
        <c:forEach items="${pageInfo.list}" var="repair">
            <tr>
                <td>${repair.repairid}</td>
                <td>${repair.repairthing}</td>
                <td>${repair.repairdes}</td>
                <td>${repair.buildid}-${repair.roomid}</td>
                <td><fmt:formatDate value="${repair.repairdatefrom}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
                <c:choose>
                    <c:when test="${repair.repairstatus eq 0}">
                        <td>未维修</td>
                    </c:when>
                    <c:otherwise>
                        <td>已维修</td>
                    </c:otherwise>
                </c:choose>
                <td>${repair.repairperson}</td>
                <td><fmt:formatDate value="${repair.repairdateend}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
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
                <a href="/dormitory/repair/done?page=${pageInfo.pageNum-1}" target="_self">上一页</a>
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
                <a href="/dormitory/repair/done?page=${pageInfo.pageNum+1}" target="_self">下一页</a>
            </c:otherwise>
        </c:choose>向第 <input id="inputpage" type="text"> 页
        <a class="skip" onclick="jump()">跳转</a>
        <script type="text/javascript">
            function jump() {
                window.location.href = "/dormitory/repair/done?page=" + $("#inputpage").val();
            }
        </script>
    </div>
</div>
<!-- 展示列表模块 end -->

<%--实时获取当前时间--%>
<script>

    window.onload = function () {
        getLongDate();
    }//定时刷新
    function getLongDate() {
        //创建当前系统时间的Date对象
        var dateObj = new Date();
        //获取完整年份值
        var year = dateObj.getFullYear();
        //获取月份
        var month = dateObj.getMonth() + 1;
        //获取月份中的日
        var date = dateObj.getDate();
        //获取星期值
        var day = dateObj.getDay();
        var weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        //根据星期值，从数组中获取对应的星期字符串
        var week = weeks[day];
        //获取小时
        var hour = dateObj.getHours();
        //获取分钟
        var minute = dateObj.getMinutes();
        //获取秒钟
        var second = dateObj.getSeconds();
        //如果月、日、时、分、秒的值小于10，在前面补0
        if (month < 10) {
            month = "0" + month;
        }
        if (date < 10) {
            date = "0" + date;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minute < 10) {
            minute = "0" + minute;
        }
        if (second < 10) {
            second = "0" + second;
        }
        global_user_date = year + "-" + month + "-" + date;
        var newDate = year + "年" + month + "月" + date + "日 " + week + " " + hour + ":" + minute + ":" + second;
        document.getElementById("nowTime").innerHTML = "[ " + newDate + " ]";
        setTimeout("getLongDate()", 1000);//每隔一秒重新调用一次该函数
    }
</script>
</body>

</html>

