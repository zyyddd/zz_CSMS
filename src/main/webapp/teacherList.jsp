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
    <title>教师界面</title>

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
        function deleteCourse(couId) {
            //用户安全提示
            if (confirm("您确定要删除吗？")) {
                //访问路径
                location.href = "${pageContext.request.contextPath}/DelCourseServlet?couId=" + couId;
            }
        }

        window.onload = function () {
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function () {
                if (confirm("您确定要删除选中条目吗？")) {

                    var flag = false;
                    //判断是否有选中条目
                    var cbs = document.getElementsByName("couId");
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
                var cbs = document.getElementsByName("couId");
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

        <h3 style="text-align: center">课程信息列表</h3>

        <div style="float: left;">

            <form class="form-inline" action="${pageContext.request.contextPath}/FindCourseByPageServlet" method="post">
                <div class="form-group">
                    <label for="couName">课程名</label>
                    <input type="text" name="couName" value="${condition.couName[0]}" class="form-control" id="couName">
                </div>
                <div class="form-group">
                    <label for="couHour">课程学时</label>
                    <select name="couHour" id="couHour" class="form-control" value="${condition.couHour[0]}">
                        <c:if test="${condition.couHour[0]==null || condition.couHour[0] == ''}">
                            <option value="" selected>---未选择---</option>
                            <option value="4">4个学分学时</option>
                            <option value="3">3个学分学时</option>
                            <option value="2">2个学分学时</option>
                        </c:if>
                        <c:if test="${condition.couHour[0]==4}">
                            <option value="">---未选择---</option>
                            <option value="4" selected>4个学分学时</option>
                            <option value="3">3个学分学时</option>
                            <option value="2">2个学分学时</option>
                        </c:if>
                        <c:if test="${condition.couHour[0]==3}">
                            <option value="">---未选择---</option>
                            <option value="4">4个学分学时</option>
                            <option value="3" selected>3个学分学时</option>
                            <option value="2">2个学分学时</option>
                        </c:if>
                        <c:if test="${condition.couHour[0]==2}">
                            <option value="">---未选择---</option>
                            <option value="4">4个学分学时</option>
                            <option value="3">3个学分学时</option>
                            <option value="2" selected>2个学分学时</option>
                        </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <label for="judge">审核状态</label>
                    <select name="judge" id="judge" class="form-control" value="${condition.judge[0]}">
                        <c:if test="${condition.judge[0] == null || condition.judge[0] == ''}">
                            <option value="" selected>---未选择---</option>
                            <option value="1">通过</option>
                            <option value="2">审核中</option>
                            <option value="3">未通过</option>
                        </c:if>
                        <c:if test="${condition.judge[0] == 1}">
                            <option value="">---未选择---</option>
                            <option value="1" selected>通过</option>
                            <option value="2">审核中</option>
                            <option value="3">未通过</option>
                        </c:if>
                        <c:if test="${condition.judge[0] == 2}">
                            <option value="">---未选择---</option>
                            <option value="1">通过</option>
                            <option value="2" selected>审核中</option>
                            <option value="3">未通过</option>
                        </c:if>
                        <c:if test="${condition.judge[0] == 3}">
                            <option value="">---未选择---</option>
                            <option value="1">通过</option>
                            <option value="2">审核中</option>
                            <option value="3" selected>未通过</option>
                        </c:if>

                    </select>
                </div>

                <div class="form-group">
                    <label for="isopen">开课状态</label>
                    <select name="isopen" id="isopen" class="form-control" value="${condition.isopen[0]}">
                        <c:if test="${condition.isopen[0] == null || condition.isopen[0] == ''}">
                            <option value="" selected>---未选择---</option>
                            <option value="1">已开课</option>
                            <option value="2">未开课</option>
                        </c:if>
                        <c:if test="${condition.isopen[0] == 1}">
                            <option value="">---未选择---</option>
                            <option value="1" selected>已开课</option>
                            <option value="2">未开课</option>
                        </c:if>
                        <c:if test="${condition.isopen[0] == 2}">
                            <option value="">---未选择---</option>
                            <option value="1">已开课</option>
                            <option value="2" selected>未开课</option>
                        </c:if>

                    </select>
                </div>

                <button type="submit" class="btn btn-default">查询</button>
            </form>

        </div>


        <div style="float: right;margin: 5px;">

            <a class="btn btn-primary" href="${pageContext.request.contextPath}/teacherAddCourse.jsp">添加课程信息</a>
            <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

        </div>

        <form id="form" action="${pageContext.request.contextPath}/DelCourseSelectedServlet" method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th><input type="checkbox" id="firstCb"></th>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>学分学时</th>
                    <th>课程描述</th>
                    <th>审核状态</th>
                    <th>开课状态</th>
                    <th>限选人数</th>
                </tr>

                <c:forEach items="${pb.list}" var="course" varStatus="s">
                    <tr>
                        <td><input type="checkbox" name="couId" value="${course.couId}"></td>
                        <td>${course.couId}</td>
                        <td>${course.couName}</td>
                        <td>${course.couHour}</td>
                        <td>${course.couDes}</td>

                        <c:if test="${course.judge==1}">
                            <td>审核通过</td>
                        </c:if>
                        <c:if test="${course.judge==2}">
                            <td>审核中</td>
                        </c:if>
                        <c:if test="${course.judge==3}">
                            <td>审核不通过</td>
                        </c:if>

                        <c:if test="${course.isopen==1}">
                            <td>已开课</td>
                        </c:if>
                        <c:if test="${course.isopen==2}">
                            <td>未开课</td>
                        </c:if>

                        <td>${course.limitNum}</td>

                        <td><a class="btn btn-default btn-sm"
                               href="${pageContext.request.contextPath}/FindCourseServlet?couId=${course.couId}">修改</a>&nbsp;
                            <a class="btn btn-default btn-sm" href="javascript:deleteCourse(${course.couId});">删除</a>
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
                            <a href="${pageContext.request.contextPath}/FindCourseByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&couName=${condition.couName[0]}&couHour=${condition.couHour[0]}&judge=${condition.judge[0]}&isopen=${condition.isopen[0]}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a
                                    href="${pageContext.request.contextPath}/FindCourseByPageServlet?currentPage=${i}&rows=5&rows=5&couName=${condition.couName[0]}&couHour=${condition.couHour[0]}&judge=${condition.judge[0]}&isopen=${condition.isopen[0]}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/FindCourseByPageServlet?currentPage=${i}&rows=5&rows=5&couName=${condition.couName[0]}&couHour=${condition.couHour[0]}&judge=${condition.judge[0]}&isopen=${condition.isopen[0]}">${i}</a>
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
                            <a href="${pageContext.request.contextPath}/FindCourseByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&rows=5&couName=${condition.couName[0]}&couHour=${condition.couHour[0]}&judge=${condition.judge[0]}&isopen=${condition.isopen[0]}"
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

