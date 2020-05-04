<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>水电账单</title>
    <%--引入基本路径--%>
    <base href="<%=basePath%>resources/">
    <!-- 字体图标css文件 -->
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1629753_4n5ive5fsq.css">
    <!-- 初始化css文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 公共css文件 -->
    <link rel="stylesheet" href="css/conmon.css">
    <!--引入jQuery文件-->
    <script src="js/jquery-3.4.1.min.js"></script>
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
        <li>
            <a href="/dormitory/notifycation/list?buildid=${user.buildid}">
                <span class="iconfont icon-tongzhigonggao icon1"></span> 通知公告
            </a>
        </li>
        <li>
            <a href="/dormitory/payment/list?buildid=${user.buildid}">
                <span class="iconfont icon-zhangdan  icon"></span> 水电账单
            </a>
        </li>
    </ul>
</section>
<!-- 侧边模块 end -->

<div class="list">
    <h2>
        账单列表
        <div class="input">
            <input id="inputheadline" name="inputheadline" type="text" placeholder="请输入水电账单标题">
        </div>
        <div class="add">
            添加水电账单
            <a href="/dormitory/payment/preadd?userid=${user.userid}" class="iconfont icon-add-fill icon"></a>
        </div>
    </h2>

    <table border="1" cellspacing="0">
        <thead>
        <tr>
            <td>水电账单id</td>
            <td class="content">水电账单标题</td>
            <td>发布时间</td>
            <td>宿舍楼</td>
            <td>操作</td>
        </tr>
        </thead>
        <c:forEach items="${pageInfo.list}" var="paymentfile">
            <tr>
                <td>${paymentfile.paymenttableid}</td>
                <td>${paymentfile.paymenthead}</td>
                <td><fmt:formatDate value="${paymentfile.paymentdate}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
                <td>${paymentfile.buildid}</td>
                <td>
                    <a href="/dormitory/payment/detail?paymenttableid=${paymentfile.paymenttableid}" class="iconfont icon-yanjing"></a>
                    <a href="/dormitory/payment/delete?paymenttableid=${paymentfile.paymenttableid}" class="iconfont icon-lajitong"></a>
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
                <a href="/dormitory/payment/list?buildid=${user.buildid}&page=${pageInfo.pageNum-1}" target="_self">上一页</a>
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
                <a href="/dormitory/payment/list?buildid=${user.buildid}&page=${pageInfo.pageNum+1}" target="_self">下一页</a>
            </c:otherwise>
        </c:choose>向第 <input id="inputpage" type="text"> 页
        <a class="skip" onclick="jump()">跳转</a>
        <script type="text/javascript">
            function jump() {
                window.location.href = "/dormitory/payment/list?buildid=${user.buildid}&page=" + $("#inputpage").val();
            }
        </script>
    </div>
</div>
<!-- 展示列表模块 end -->

<%--回车事件触发，搜索水电账单业务执行--%>
<script type="text/javascript">
    $("#inputheadline").keydown(function (t) {
        if(t.keyCode == 13){
            window.location.href = "/dormitory/payment/find?buildid=${user.buildid}&searchKey="+$("#inputheadline").val()
        }
    })
</script>

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