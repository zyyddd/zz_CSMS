<%@ page import="indi.domain.User" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2021/10/29
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>系统管理员界面</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>

        function deleteCourse(account,couName) {
            //用户安全提示
            if (confirm("您确定要删除吗？")) {
                //访问路径
                location.href = "${pageContext.request.contextPath}/StudentServlet?action=delCourse&account=" + account + "&couName=" + couName;
            }
        }

        function selectCourse(account, couName, s) {
            //用户安全提示

            var options = $("#teaName" + s + " option:selected");
            var teaName = options.text();

            var teaNameval = options.val();
            if (teaName != null && teaNameval != 1 && teaName !== '') {
                if (confirm("您确定要选择吗？")) {
                    //访问路径
                    location.href = "${pageContext.request.contextPath}/StudentServlet?action=selectCourse&account=" + account + "&couName=" + couName + "&teaName=" + teaName;
                }
            }
        }


    </script>
</head>
<body>
<div>

    <div class="container">



        <%
            User user = (User) session.getAttribute("user");
            String account = user.getAccount();
            String name = user.getName();
            session.setAttribute("account",account);
            session.setAttribute("name",name);
        %>


        <br>

        <%--课程表显示和退选功能--%>
        <form id="form" action="${pageContext.request.contextPath}/StudentServlet?action=showCourse" method="post">



                <h3 style="text-align: center">${requestScope.get("name")}学生课程列表</h3>

            <table border="1" class="table table-bordered table-hover">

                <tr class="success">

                    <th>编号</th>
                    <th>已选课程名</th>
                    <th>该课程任课老师</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${maps1}" var="user" varStatus="s">
                    <tr>

                        <td>${s.count}</td>
                        <td>${user.get("couId")}</td><%--显示的是课程名字 是String--%>
                        <td>${user.get("teaId")}</td>
                        <td>
                            <a class="btn btn-default btn-sm" href="javascript:deleteCourse('${sessionScope.get("account")}','${user.get("couId")}');">退选</a></td>
                    </tr>


                </c:forEach>


            </table>
            <br>
            <br>
            <br>




        <%--未选择课程显示--%>


                <h3 style="text-align: center">${requestScope.get("name")}学生可选课程列表</h3>

                <%--选课功能--%>
                <table border="1" class="table table-bordered table-hover">

                    <tr class="success">

                        <th>编号</th>
                        <th>未选课程名</th>
                        <th>任课老师选择</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach items="${maps2}" var="user2" varStatus="s">
                        <tr>

                            <td>${s.count}</td>
                            <td>${user2.get("couName")}</td>

                            <td>
                                <select name="teaName${s.count}" id="teaName${s.count}" class="form-control" required>
                                    <option value="1" selected>---请选择---</option>
                                    <c:forEach items='${user2.get("teaName")}' var="teaname">
                                        <option value="">${teaname.get("name")}</option>
                                    </c:forEach>

                                </select>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm"
                                   href="javascript:selectCourse('${sessionScope.get("account")}','${user2.get("couName")}',${s.count});">选择</a>
                            </td>
                        </tr>

                    </c:forEach>


                </table>
            </form>

    </div>

</div>
</body>
</html>