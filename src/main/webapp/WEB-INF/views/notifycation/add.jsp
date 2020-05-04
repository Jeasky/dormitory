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
    <base href="<%= basePath%>resources/">
    <!-- 字体图标css文件 -->
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1629753_4m2l2q8ewm9.css">
    <!-- 初始化css文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 公共css文件 -->
    <link rel="stylesheet" href="css/conmon.css">
    <!-- 引入页面css文件 -->
    <link rel="stylesheet" href="css/manage.css">
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
                <input type="file" name="file" id="file">
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
            <a href="/dormitory/payment/list">
                <span class="iconfont icon-zhangdan  icon"></span> 水电账单
            </a>
        </li>
    </ul>
</section>
<!-- 侧边模块 end -->
<div class="infrom">
        <div class="infrom-hd">
            通知标题:
            <input id="notifycationhead" type="text" placeholder="请输入通知内容">
        </div>

        <div class="infrom-content">
            通知内容:
            <textarea name="notifycationtext" id="notifycationtext" cols="30" rows="10" placeholder="请输入通知内容"></textarea>
        </div>
        <div class="release-unit">
            发布单位:
            <input id="promulgator" type="text" placeholder="请输入发布单位">
            <a href="/dormitory/notifycation/list?buildid=${user.buildid}" class="cancel">取消</a>
            <a id="publish" class="publish">发布</a>
        </div>
</div>

<script>
    $("#publish").click(function () {
        $.ajax({
            url: "<%=basePath%>notifycation/add",
            type: "POST",
            data: {
                "buildid": ${user.buildid},
                "notifycationhead": $("#notifycationhead").val(),
                "notifycationtext": $("#notifycationtext").val(),
                "promulgator": $("#promulgator").val()
            },
            dataType: "text",
            success: function (data) {
                alert("添加成功！");
                location.href= "<%=basePath%>notifycation/list?buildid=${user.buildid}";
            }
        })
    });
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
