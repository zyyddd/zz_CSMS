<%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2021/10/28
  Time: 10:07
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
    <title>学生成绩界面</title>

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




    </script>
</head>
<body>
<div>

    <div class="container">



        <div style="float: left;">


        </div>




        <form id="form" action="${pageContext.request.contextPath}/StudentServlet?action=showScore" method="post">
            <h3 style="text-align: center">${requestScope.get("name")}同学的成绩列表</h3>

            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th>编号</th>
                    <th>学科名称</th>
                    <th>学科成绩</th>
                    <th>学科最高分</th>
                    <th>学科最低分</th>
                    <th>学科平均分</th>
                    <th>学科排名</th>
                </tr>

                <c:forEach items="${studentScoreList}" var="ss"  varStatus="s">
                    <tr>

                        <td>${s.count}</td>
                        <td>${ss.get(0)}</td>
                        <td>${ss.get(1)}</td>
                        <td>${ss.get(2)}</td>
                        <td>${ss.get(3)}</td>
                        <td>${ss.get(4)}</td>
                        <td>${ss.get(5)}</td>

                    </tr>

                </c:forEach>


            </table>
        </form>



    </div>

</div>
</body>
</html>

