<%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2021/11/3
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>个人网页</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            background-color: skyblue;
            font-size: 20px;
            background: url("imgs/aboutBackground.jpg");
            background-repeat: no-repeat;
            background-size: cover;
        }

        li {
            list-style: none;
            margin-bottom: 20px;
        }

        .personnal {
            float: left;
            margin: 50px 0 0 500px;
        }

        .musics {
            margin-left: 150px;
        }

        img {
            height: 500px;
            width: 500px;
        }
    </style>

</head>
<body>

<div>
    <div class="personnal">
        <audio autoplay="autoplay" controls="controls" loop="loop" preload="auto"
               src="./001.mp3" class="musics">
        </audio>
        <ul>
            <li><strong>姓名：郑鑫凡</strong></li>
            <li>性别：男</li>
            <li>学号：201910244232</li>
            <li>班级：计算机科学与技术192</li>

            <li>联系方式：手机号码15976924510/13144452325 qq1030935903</li>

        </ul>
        <ul>
            <li><strong>姓名：郑煜东</strong></li>
            <li>性别：男</li>
            <li>学号：201910244233</li>
            <li>班级：计算机科学与技术192</li>

            <li>联系方式：手机号码18316862056 qq779393905</li>

        </ul>
    </div>
    <div style="float: left;">
        <img src="imgs/talk01.jpg" style="margin-left: 300px">
        <img src="imgs/talk02.jpg" style="margin-left: 100px">
    </div>
</div>
</body>
</html>
