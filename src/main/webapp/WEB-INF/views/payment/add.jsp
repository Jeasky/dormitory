<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <span>2020年03月21日 20:00:52 星期六</span>
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
            <input id="paymentfile" name="paymentfile" type="file" accept="xls/xlsx" placeholder="请选择水电账单文件">
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
        var paymentfile = document.getElementById("paymentfile");
        var ssFile = document.getElementById("ssFile");
        ssFile.value = paymentfile.value.substring(12);    //取出文件名，并赋值回显到文本框，用于向后台传文件名
        $.ajax({
            url : "<%=basePath%>payment/add", //用于文件上传的服务器端请求地址
            fileElementId : 'paymentfile', //文件上传空间的id属性  <input type="file" id="paymentfile" name="paymentfile" />
            data : {
                "buildid": ${user.buildid},
                "paymenthead": $("#paymenthead").val()
            },
            processData: false, //因为data值是FormData对象，不需要对数据做处理。
            contentType: false,
            type : 'POST',
            dataType : 'text', //返回值类型 一般设置为json
            success : function(data, status) //服务器成功响应处理函数
            {
                alert("文件上传成功");
                location.href= "<%=basePath%>payment/list?buildid=${user.buildid}";
            },
            error : function(data, status, e)//服务器响应失败处理函数
            {
                alert("文件上传失败!"+data.responseJSON);

            }
        })
    });
</script>

<%--<script>--%>
    <%--$("#addBtn").click(function () {--%>
        <%--$.ajax({--%>
            <%--url: "<%=basePath%>payment/add",--%>
            <%--type: "POST",--%>
            <%--data: {--%>
                <%--"buildid": ${user.buildid},--%>
                <%--"paymenthead": $("#paymenthead").val(),--%>
                <%--"paymentfile": $("#paymentfile").val()--%>
            <%--},--%>
            <%--dataType: "text",--%>
            <%--success: function (data) {--%>
                <%--alert("添加成功！");--%>
                <%--location.href= "<%=basePath%>payment/list?buildid=${user.buildid}";--%>
            <%--}--%>
        <%--})--%>
    <%--});--%>
<%--</script>--%>
</html>