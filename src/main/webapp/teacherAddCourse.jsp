<%--
  Created by AFun.
  User: AFun
  Date: 2021/10/28
  Time: 8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5文档-->
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
    <title>增加新的课程信息</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>

    <script>
        function jump() {
            window.location.href = "${pageContext.request.contextPath}/FindCourseByPageServlet";
        }
    </script>

</head>
<body>
<div class="container">
    <center><h3>增加新的课程信息</h3></center>
    <form action="${pageContext.request.contextPath}/AddCourseServlet" method="post">

        <div class="form-group">
            <label for="couName">课程名称：</label>
            <input type="text" class="form-control" id="couName" name="couName" placeholder="请输入课程名称">
        </div>

        <div class="form-group">
            <label for="couHour">学分课时：</label>
            <input type="text" class="form-control" id="couHour" name="couHour" placeholder="请输入课程学时">
        </div>

        <div class="form-group">
            <label for="couDes">课程简介：</label>
            <input type="text" class="form-control" id="couDes" name="couDes" placeholder="请输入课程简介">
        </div>

        <div class="form-group">
            <label for="limitNum">限选人数：</label>
            <input type="text" class="form-control" id="limitNum" name="limitNum" placeholder="请输入限选人数">
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
