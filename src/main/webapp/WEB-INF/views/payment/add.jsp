<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <title>添加水电账单</title>
    <base href="<%= basePath%>resources/">
    <!-- 字体图标css文件 -->
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_1629753_2rv309tgnn5.css">
    <!-- 初始化css文件 -->
    <link rel="stylesheet" href="css/base.css">
    <!-- 公共css文件 -->
    <link rel="stylesheet" href="css/conmon.css">
    <!-- 页面css文件 -->
    <link rel="stylesheet" href="css/payment.css">
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
    <span id="nowTime"></span>
</div>
<!-- 时间模块 end-->

<!-- 侧边模块 start -->
<section>
    <ul>
        <li>
            <a href="javascript:;">
                <span class="iconfont icon-tianjiazhangdan icon1"></span> 添加水电账单
            </a>
        </li>
    </ul>
</section>
<!-- 侧边模块 end -->
<div class="infrom">
        <div class="infrom-hd">
            水电账单标题:
            <input id="paymenthead" type="text" placeholder="请输入水电账单标题">
        </div>

        <div class="release-unit">
            水电账单文件:
            <input id="paymentfile" name="paymentfile" type="file" placeholder="请选择水电账单文件">
            <input type="hidden"  id="ssFile" name="ssFile"> <!--用于文件名回显-->
        </div>

        <div class="submit">
            <a href="/dormitory/payment/list?buildid=${user.buildid}" class="cancel">取消</a>
            <a id="addBtn" class="publish">添加</a>
        </div>
</div>
</body>

<script>
    $("#addBtn").click(function () {
        var fileObj = document.getElementById("paymentfile").files[0]; // js 获取文件对象
        var myfrom= new window.FormData();
        myfrom.append("paymentfile",fileObj);
        myfrom.append("paymenthead",$("#paymenthead").val());
        myfrom.append("buildid",${user.buildid});
        $.ajax({
            url :  "<%=basePath%>payment/add",
            data : myfrom,
            type : "POST",
            datatype: "json",
            async: true,
            cache: false,
            contentType: false,
            processData: false,
            success : function(data, status) //服务器成功响应处理函数
            {
                location.href= "<%=basePath%>payment/list?buildid=${user.buildid}";
            },
            error : function(data, status, e)//服务器响应失败处理函数
            {
                alert("文件上传失败!"+data.responseJSON);
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
</html>