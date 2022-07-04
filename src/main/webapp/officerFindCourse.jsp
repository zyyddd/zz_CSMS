<%--
  Created by AFun.
  User: AFun
  Date: 2021/10/30
  Time: 17:00
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
        function alterJudge() {
            // 用户安全提示
            if (confirm("您确定要提交此次审核状态吗？")) {
                document.getElementById("alterForm").submit();
            }
        }
    </script>
</head>
<body>
<div>

    <div class="container">

        <h3 style="text-align: center">教务处管理员审核</h3>

        <div style="float: left;">

            <form class="form-inline" action="${pageContext.request.contextPath}/FindJudgeByPageServlet" method="post">
                <div class="form-group">
                    <label for="couName">课程名称</label>
                    <input type="text" name="couName" value="${condition.couName[0]}" class="form-control" id="couName">
                </div>
                <div class="form-group">
                    <label for="judge">审核状态</label>
                    <select name="judge" id="judge" class="form-control" value="${condition.judge[0]}">
                        <c:if test="${condition.judge[0] == null || condition.judge[0] == '' }">
                            <option value="" selected>---请选择---</option>
                            <option value="1">审核通过</option>
                            <option value="2">审核中</option>
                            <option value="3">审核不通过</option>
                        </c:if>
                        <c:if test="${condition.judge[0] == 1}">
                            <option value="">---请选择---</option>
                            <option value="1" selected>审核通过</option>
                            <option value="2">审核中</option>
                            <option value="3">审核不通过</option>
                        </c:if>
                        <c:if test="${condition.judge[0] == 2}">
                            <option value="">---请选择---</option>
                            <option value="1">审核通过</option>
                            <option value="2" selected>审核中</option>
                            <option value="3">审核不通过</option>
                        </c:if>
                        <c:if test="${condition.judge[0] == 3}">
                            <option value="">---请选择---</option>
                            <option value="1">审核通过</option>
                            <option value="2">审核中</option>
                            <option value="3" selected>审核不通过</option>
                        </c:if>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>

        </div>


        <div style="float: right;margin: 5px;">
            <input class="btn btn-primary" type="button" value="提交" onclick="alterJudge()">
        </div>

        <form id="alterForm" action="${pageContext.request.contextPath}/AlterJudgeServlet" method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th>课程号</th>
                    <th>课程名称</th>
                    <th>审核状态</th>
                    <th>开课状态</th>
                </tr>

                <c:forEach items="${pb.list}" var="course" varStatus="s">
                    <tr>
                        <!-- 隐藏域 提交couId -->
                        <input type="hidden" name="couId" value="${course.couId}">
                        <td>${course.couId}</td>
                        <td>${course.couName}</td>
                        <td>
                            <select name="judge" id="alterJudgeSelect">
                                <c:if test="${course.judge ==1}">
                                    <option value="1">审核通过</option>
                                </c:if>
                                <c:if test="${course.judge ==2}">
                                    <option value="1">审核通过</option>
                                    <option value="2" selected>审核中</option>
                                    <option value="3">审核不通过</option>
                                </c:if>
                                <c:if test="${course.judge ==3}">
                                    <option value="1">审核通过</option>
                                    <option value="2">审核中</option>
                                    <option value="3" selected>审核不通过</option>
                                </c:if>
                            </select>
                        </td>
                        <td>
                            <select name="isopen" id="alterIsopen">
                                <c:if test="${course.isopen ==1}">
                                    <option value="1" selected>已开课</option>
                                    <option value="2">未开课</option>
                                </c:if>
                                <c:if test="${course.isopen ==2}">
                                    <option value="1">已开课</option>
                                    <option value="2" selected>未开课</option>
                                </c:if>
                            </select>
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
                            <a href="${pageContext.request.contextPath}/FindJudgeByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&couName=${condition.couName[0]}&judge=${condition.judge[0]}&roleId=${condition.roleId[0]}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a
                                    href="${pageContext.request.contextPath}/FindJudgeByPageServlet?currentPage=${i}&rows=5&couName=${condition.couName[0]}&judge=${condition.judge[0]}&roleId=${condition.roleId[0]}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/FindJudgeByPageServlet?currentPage=${i}&rows=5&couName=${condition.couName[0]}&judge=${condition.judge[0]}&roleId=${condition.roleId[0]}">${i}</a>
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
                            <a href="${pageContext.request.contextPath}/FindJudgeByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&couName=${condition.couName[0]}&judge=${condition.judge[0]}&roleId=${condition.roleId[0]}"
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

