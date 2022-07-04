<%--
  Created by AFun.
  User: AFun
  Date: 2021/10/27
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <%--    	<base href="<%=basePath%>"/>--%>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <script>
        function jump() {
            window.location.href = "${pageContext.request.contextPath}/FindUserByPageServlet";
        }
    </script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改用户信息</h3>
    <form action="${pageContext.request.contextPath}/UpdateUserServlet" method="post">
        <!-- 隐藏域 提交id -->
        <input type="hidden" name="uid" value="${user.uid}">

        <div class="form-group">
            <label for="account">账号：</label>
            <input type="text" class="form-control" id="account" name="account" value="${user.account}"
                   placeholder="请输入账号" readonly/>
        </div>
        <div class="form-group">
            <input type="hidden" class="form-control" id="password" name="password" value="${user.password}"
                   placeholder="请输入密码"/>
        </div>

        <div class="form-group">
            <label>角色：</label>
            <c:if test="${user.roleId == 1 }">
                <input type="radio" name="roleId" value="1" checked/>系统管理员
                <input type="radio" name="roleId" value="2"/>教务处管理员
                <input type="radio" name="roleId" value="3"/>教师
                <input type="radio" name="roleId" value="4"/>学生
            </c:if>
            <c:if test="${user.roleId == 2 }">
                <input type="radio" name="roleId" value="1"/>系统管理员
                <input type="radio" name="roleId" value="2" checked/>教务处管理员
                <input type="radio" name="roleId" value="3"/>教师
                <input type="radio" name="roleId" value="4"/>学生
            </c:if>
            <c:if test="${user.roleId == 3 }">
                <input type="radio" name="roleId" value="1"/>系统管理员
                <input type="radio" name="roleId" value="2"/>教务处管理员
                <input type="radio" name="roleId" value="3" checked/>教师
                <input type="radio" name="roleId" value="4"/>学生
            </c:if>
            <c:if test="${user.roleId == 4 }">
                <input type="radio" name="roleId" value="1"/>系统管理员
                <input type="radio" name="roleId" value="2"/>教务处管理员
                <input type="radio" name="roleId" value="3"/>教师
                <input type="radio" name="roleId" value="4" checked/>学生
            </c:if>

        </div>

        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="请输入姓名"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${user.gender == '男'}">
                <input type="radio" name="gender" value="男" checked/>男
                <input type="radio" name="gender" value="女"/>女
            </c:if>
            <c:if test="${user.gender == '女'}">
                <input type="radio" name="gender" value="男"/>男
                <input type="radio" name="gender" value="女" checked/>女
            </c:if>
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回" onclick="javascript:jump()"/>
        </div>
    </form>
</div>
</body>
</html>