<%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2021/11/2
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset =utf-8">
    <title>教务处管理员界面</title>

    <style>
        body {
            margin: auto;
            padding: 0;
            border: 1px;
            height: 85%;
            background-color: antiquewhite;
        }


        #frameContentLeft {

            position: relative;
            float: left;
            height: 630px;
            width: 15%;
            vertical-align: top;
            margin-left: 1px;
            background-color: rgb(250, 255, 220);
        }

        #frameContentRight {
            position: relative;
            float: left;
            height: 630px;
            width: 84%;
            margin-left: 3px;
            background-color: rgb(250, 255, 220);
        }

        td {
            text-align: center;
            vertical-align: center;
        }

        * {
            margin: 0;
            padding: 0;
        }

        li {
            list-style: none ;
        }
        a {
            text-decoration: none;
        }


        .nav li {
            float: left;
            margin: 0 85px;
            padding-top:21px;
        }
        .nav a{
            height: 33px;

            padding-left: 10px;
            color: #0f0f0f;
            display: block;
            line-height: 33px;
            font-weight: 700;
            font-size:14px;

        }

    </style>
</head>
<body>


<%--顶部--%>
<jsp:include page="/top.jsp"/>

<%--中间--%>
<div style="width:100%;top:70px;height:630px;float: left;position: fixed">


    <!--左边-->
    <div id="frameContentLeft">


        <table style="height:100% ;width: 100%">
            <tr>
                <td><a href="${pageContext.request.contextPath}/FindJudgeByPageServlet" target="frameContentRight">课程设置审批</a></td>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/FindCountByPageServlet" target="frameContentRight">统计</a></td>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/FindCourseMessageByPageServlet" target="frameContentRight">选课结果管理</a></td>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/FindScoreByPageServlet"
                       target="frameContentRight">成绩单生成</a></td>
            </tr>


        </table>
    </div>


    <!--右边-->
    <iframe id="frameContentRight" name="frameContentRight" src="right.jsp" frameborder="1"></iframe>


</div>


<jsp:include page="/down.jsp"/>

</body>
</html>

