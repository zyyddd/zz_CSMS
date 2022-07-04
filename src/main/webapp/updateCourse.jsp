<%--
  Created by AFun.
  User: AFun
  Date: 2021/10/28
  Time: 10:47
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
    <title>修改课程信息</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <script>
        function jump() {
            window.location.href = "${pageContext.request.contextPath}/FindCourseByPageServlet";
        }
    </script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改课程信息</h3>
    <form action="${pageContext.request.contextPath}/UpdateCourseServlet" method="post">
        <!-- 隐藏域 提交id -->
        <input type="hidden" name="couId" value="${course.couId}">

        <div class="form-group">
            <label for="couName">课程名：</label>
            <input type="text" class="form-control" id="couName" name="couName" value="${course.couName}"
                   placeholder="请输入课程名"/>
        </div>

        <div class="form-group">
            <label>学分学时：</label>
            <c:if test="${course.couHour == 4 }">
                <input type="radio" name="couHour" value="4" checked/>4个学分学时
                <input type="radio" name="couHour" value="3"/>3个学分学时
                <input type="radio" name="couHour" value="2"/>2个学分学时
            </c:if>
            <c:if test="${course.couHour == 3 }">
                <input type="radio" name="couHour" value="4"/>4个学分学时
                <input type="radio" name="couHour" value="3" checked/>3个学分学时
                <input type="radio" name="couHour" value="2"/>2个学分学时
            </c:if>
            <c:if test="${course.couHour == 2 }">
                <input type="radio" name="couHour" value="4"/>4个学分学时
                <input type="radio" name="couHour" value="3"/>3个学分学时
                <input type="radio" name="couHour" value="2" checked/>2个学分学时
            </c:if>
        </div>

        <div class="form-group">
            <label for="couDes">课程描述：</label>
            <input type="text" class="form-control" id="couDes" name="couDes" value="${course.couDes}"
                   placeholder="请输入课程简介"/>
        </div>

        <div class="form-group">
            <label for="limitNum">限选人数：</label>
            <input type="text" class="form-control" id="limitNum" name="limitNum" value="${course.limitNum}"
                   placeholder="请输入限选人数"/>
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