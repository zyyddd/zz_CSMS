<%--
  Created by AFun.
  User: AFun
  Date: 2021/11/5
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by AFun.
  User: AFun
  Date: 2021/10/28
  Time: 8:31
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
    <title>教师选择课程界面</title>

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
        function selectCourse(couId) {
            //用户安全提示
            if (confirm("您确定要选择此课程吗？")) {
                var teaId =${teaId};
                //访问路径
                location.href = "${pageContext.request.contextPath}/SelectThisCourseServlet?couId=" + couId + "&teaId=" + teaId;
            }
        }
    </script>
</head>
<body>
<div>

    <div class="container">

        <h3 style="text-align: center">可选课程列表</h3>

        <div style="float: left;">

            <form class="form-inline" action="${pageContext.request.contextPath}/TeacherSelectServlet" method="post">
                <div class="form-group">
                    <label for="couId">课程号</label>
                    <input type="text" name="couId" value="${condition.couId[0]}" class="form-control" id="couId">
                </div>
                <div class="form-group">
                    <label for="couName">课程名</label>
                    <input type="text" name="couName" value="${condition.couName[0]}" class="form-control" id="couName">
                </div>

                <button type="submit" class="btn btn-default">查询</button>
            </form>

        </div>

        <form id="form" action="${pageContext.request.contextPath}/TeacherSelectServlet" method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th><input type="checkbox" id="firstCb"></th>
                    <th>课程号</th>
                    <th>课程名</th>
                </tr>

                <c:forEach items="${pb.list}" var="course" varStatus="s">
                    <tr>
                        <td>${course.couId}</td>
                        <td>${course.couName}</td>

                        <td>
                            <a class="btn btn-default btn-sm" href="javascript:selectCourse(${course.couId});">选择</a>
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
                            <a href="${pageContext.request.contextPath}/TeacherSelectServlet?currentPage=${pb.currentPage - 1}&rows=5&couId=${condition.couId[0]}&couName=${condition.couName[0]}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a
                                    href="${pageContext.request.contextPath}/TeacherSelectServlet?currentPage=${i}&rows=5&couId=${condition.couId[0]}&couName=${condition.couName[0]}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/TeacherSelectServlet?currentPage=${i}&rows=5&couId=${condition.couId[0]}&couName=${condition.couName[0]}">${i}</a>
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
                            <a href="${pageContext.request.contextPath}/TeacherSelectServlet?currentPage=${pb.currentPage + 1}&rows=5&rows=5&couId=${condition.couId[0]}&couName=${condition.couName[0]}"
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

