<%--
  Created by AFun.
  User: AFun
  Date: 2021/11/3
  Time: 14:15
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
    </script>

</head>
<body>

<div class="container" style="width: 400px;margin-top: 80px">
    <h3 style="text-align: center;">修改密码</h3>
    <form action="${pageContext.request.contextPath}/AlterPasswordServlet" method="post">
        <!-- 隐藏域 提交id -->
        <input type="hidden" name="uid" value="${user.uid}">

        <div class="form-group">
            <label for="account">账号：</label>
            <input type="text" class="form-control" id="account" name="account" value="${user.account}"
                   placeholder="" readonly/>
        </div>

        <div class="form-group">
            <label for="oldPassword">旧密码：</label>
            <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                   placeholder="请输入您的旧密码"/>
        </div>

        <div class="form-group">
            <label for="password">新密码：</label>
            <input type="password" class="form-control" id="password" name="password"
                   placeholder="请输入您的新密码"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="button" value="返回" onclick="history.back(-1);"/>
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert">
            <span>&times;</span></button>
        <strong>${pageContext.request.getAttribute("alter_msg")}</strong>
    </div>
</div>
</body>
</html>
