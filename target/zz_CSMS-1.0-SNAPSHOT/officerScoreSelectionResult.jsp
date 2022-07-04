<%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2021/11/2
  Time: 9:07
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
    <title>教务员选课结果管理页面</title>

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
        function deleteCourse(stuId,couId) {
            //用户安全提示
            if (confirm("您确定要删除吗？")) {
                //访问路径
                location.href = "${pageContext.request.contextPath}/DelCourseSelectionResultServlet?stuId=" + stuId+"&couId="+couId;
            }
        }

        window.onload = function () {
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function () {
                if (confirm("您确定要删除选中条目吗？")) {

                    var flag = false;
                    //判断是否有选中条目
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            //有一个条目选中了
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {//有条目被选中
                        //表单提交
                        document.getElementById("form").submit();
                    }

                }

            }
            //1.获取第一个cb
            document.getElementById("firstCb").onclick = function () {
                //2.获取下边列表中所有的cb
                var cbs = document.getElementsByName("uid");
                //3.遍历
                for (var i = 0; i < cbs.length; i++) {
                    //4.设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;

                }

            }


        }


    </script>
</head>
<body>
<div>

    <div class="container">

        <h3 style="text-align: center">教务员成绩单生成页面</h3>


        <div style="float: left;">

            <form class="form-inline" action="${pageContext.request.contextPath}/FindScoreByPageServlet" method="post">
                <div class="form-group">
                    <label for="name">学号</label>
                    <input type="text" name="stuId" value="${condition.stuId[0]}" class="form-control" id="stuId">
                </div>
                <div class="form-group">
                    <label for="name">姓名</label>
                    <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="name">
                </div>
                <div class="form-group">
                    <label for="couName">课程</label>
                    <input type="text" name="couName" value="${condition.couName[0]}" class="form-control" id="couName">
                </div>


                <button type="submit" class="btn btn-default">查询</button>
            </form>

        </div>

        <form id="form" action="${pageContext.request.contextPath}/FindScoreByPageServlet" method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th><input type="checkbox" id="firstCb"></th>
                    <th>编号</th>
                    <th>学生学号</th>
                    <th>学生姓名</th>
                    <th>课程名称</th>
                    <th>成绩</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pb.list}" var="user" varStatus="s">
                    <tr>
                        <td><input type="checkbox" name="couName" value="${user.couName}"></td>
                        <td>${s.count}</td>
                        <td>${user.stuId}</td>
                        <td>${user.name}</td>
                        <td>${user.couName}</td>
                        <td>${user.score}</td>
                        <td>
                            <a class="btn btn-default btn-sm" href="javascript:deleteCourse('${user.stuId}','${user.couId}');">删除成绩</a></td>
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
                            <a href="${pageContext.request.contextPath}/FindScoreByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${condition.name[0]}&couName=${condition.couName[0]}&stuId=${condition.stuId[0]}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a
                                    href="${pageContext.request.contextPath}/FindScoreByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&couName=${condition.couName[0]}&stuId=${condition.stuId[0]}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/FindScoreByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&couName=${condition.couName[0]}&stuId=${condition.stuId[0]}">${i}</a>
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
                            <a href="${pageContext.request.contextPath}/FindScoreByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${condition.name[0]}&couName=${condition.couName[0]}&stuId=${condition.stuId[0]}"
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
