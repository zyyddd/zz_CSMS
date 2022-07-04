<%--
  Created by AFun.
  User: AFun
  Date: 2021/10/17
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>用户登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function refreshCode() {
            var vcode = document.getElementById("vcode");
            vcode.src = "${pageContext.request.contextPath}/CheckCodeServlet?time=" + new Date().getTime();
        }
    </script>


    <style type="text/css">
        body {
            background-image: url("imgs/zhku.png");
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>

</head>
<body>

<div class="container" style="width: 400px;margin-top: 80px">
    <h3 style="text-align: center;">欢迎来到选课管理系统</h3>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
        <div class="form-group">
            <label for="account">账号：</label>
            <input type="text" name="account" class="form-control" id="account" placeholder="请输入账号"/>
        </div>
        <br>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>
        <br>
        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码"
                   style="width: 120px;"/>
            <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/CheckCodeServlet"
                                                    title="看不清点击刷新" id="vcode"/></a>
        </div>
        <br>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert">
            <span>&times;</span></button>
        <strong>${pageContext.request.getAttribute("login_msg")}</strong>
    </div>
</div>
</body>
</html>
