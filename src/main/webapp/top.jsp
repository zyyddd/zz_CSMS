<%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2021/11/2
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #frameTop {
            position: fixed;
            top: 0;
            margin: auto;
            height: 10%;
            width: 100%;
            vertical-align: middle;
            background-color: antiquewhite;
        }
    </style>
</head>
<body>
<!--顶部-->
<div id="frameTop">
    <div align="left" style="width: 200px;height: 70px;text-align: center;line-height: 70px;float:left;">
        <img src="imgs/title.jpg" style="height: 70px;width: 600px; margin-top: 5px;margin-left: 2px">
    </div>

    <%--导航栏--%>
    <div class="nav" style="float: left;height: 70px;text-align: center;line-height: 70px;right: 0;position: fixed">
        <ul>
            <li><a href="${pageContext.request.contextPath}/login.jsp"><span>注销</span></a></li>
            <li><a href="${pageContext.request.contextPath}/alterPwd.jsp"><span>修改密码</span></a></li>
            <li><a href="${pageContext.request.contextPath}/aboutUs.jsp"><span>关于作者</span></a></li>
            <img src="imgs/zhkulogo.jpg" style="height: 65px;width: 65px;margin-top: 3px">
        </ul>
    </div>


</div>

</body>
</html>
