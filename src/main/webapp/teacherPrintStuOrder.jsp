<%--
  Created by AFun.
  User: AFun
  Date: 2021/10/29
  Time: 10:42
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
    <title>学生信息界面</title>

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
        function recordScore() {
            //用户安全提示
            if (confirm("您确定要提交成绩吗？")) {
                document.getElementById("scoreForm").submit();
            }
        }
    </script>

</head>
<body>
<div>

    <div class="container">

        <h3 style="text-align: center">学生名单</h3>

        <div style="float: left;">

            <form class="form-inline" action="${pageContext.request.contextPath}/FindStudentByPageServlet"
                  method="post">
                <div class="form-group">
                    <label for="couName">课程名</label>
                    <input type="text" name="couName" value="${condition.couName[0]}" class="form-control" id="couName">
                </div>
                <div class="form-group">
                    <label for="stuId">学生id</label>
                    <input type="text" name="stuId" value="${condition.stuId[0]}" class="form-control" id="stuId">
                </div>
                <div class="form-group">
                    <label for="name">学生姓名</label>
                    <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="name">
                </div>

                <button type="submit" class="btn btn-default">查询</button>
            </form>

        </div>

        <div style="float: right;margin: 5px;">

            <%--            <a class="btn btn-primary" href="javascript:void(0);" id = "recordScore">提交</a>--%>
            <input class="btn btn-primary" type="button" value="提交" onclick="recordScore()">
        </div>

        <form id="scoreForm" action="${pageContext.request.contextPath}/RecordStudentScoreServlet" method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th>课程名</th>
                    <th>学生学号</th>
                    <th>学生姓名</th>
                    <th>学生性别</th>
                    <th>学生成绩</th>
                </tr>

                <c:forEach items="${pb.list}" var="order" varStatus="s">
                    <tr>
                            <%--                        <td><input type="checkbox" name="couId" value="${order.couId}"></td>--%>
                        <td style="display: none"><input type="hidden" name="ssId" value="${order.ssId}"></td>
                        <td>${order.couName}</td>
                        <td>${order.stuId}</td>
                        <td>${order.stuName}</td>
                        <td>${order.gender}</td>
                        <td><input type="text" name="score" value="${order.score}" style="border:0px;text-align:center">
                        </td>
                    </tr>
                </c:forEach>


            </table>
        </form>
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${pb.currentPage == 1}">
                        <li class="disabled">
                            <span aria-hidden="true">&laquo;</span>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != 1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindStudentByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&couName=${condition.couName[0]}&stuId=${condition.stuId[0]}&stuName=${condition.stuName[0]}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a
                                    href="${pageContext.request.contextPath}/FindStudentByPageServlet?currentPage=${i}&rows=5&couName=${condition.couName[0]}&stuId=${condition.stuId[0]}&stuName=${condition.stuName[0]}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/FindStudentByPageServlet?currentPage=${i}&rows=5&couName=${condition.couName[0]}&stuId=${condition.stuId[0]}&stuName=${condition.stuName[0]}">${i}</a>
                            </li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pb.currentPage == pb.totalPage}">
                        <li class="disabled">
                            <span aria-hidden="true">&raquo;</span>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != pb.totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindStudentByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&couName=${condition.couName[0]}&stuId=${condition.stuId[0]}&stuName=${condition.stuName[0]}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

                </ul>
            </nav>


        </div>


    </div>

</div>
</body>
</html>


