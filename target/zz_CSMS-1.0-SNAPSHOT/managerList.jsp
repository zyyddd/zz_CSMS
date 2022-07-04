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
        function deleteUser(uid) {
            //用户安全提示
            if (confirm("您确定要删除吗？")) {
                //访问路径
                location.href = "${pageContext.request.contextPath}/DelUserServlet?uid=" + uid;
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

    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/FindUserByPageServlet" method="post">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="name">
            </div>
            <div class="form-group">
                <label for="uid">用户id</label>
                <input type="text" name="uid" value="${condition.uid[0]}" class="form-control" id="uid">
            </div>

            <div class="form-group">
                <label for="roleId">角色</label>
                <select name="roleId" id="roleId" class="form-control" value="${condition.roleId[0]}">
                    <c:if test="${condition.roleId[0] == null || condition.roleId[0] == '' }">
                        <option value="" selected>---请选择---</option>
                        <option value="1">系统管理员</option>
                        <option value="2">教务处管理员</option>
                        <option value="3">教师</option>
                        <option value="4">学生</option>
                    </c:if>
                    <c:if test="${condition.roleId[0] == 1}">
                        <option value="">---请选择---</option>
                        <option value="1" selected>系统管理员</option>
                        <option value="2">教务处管理员</option>
                        <option value="3">教师</option>
                        <option value="4">学生</option>
                    </c:if>
                    <c:if test="${condition.roleId[0] == 2}">
                        <option value="">---请选择---</option>
                        <option value="1">系统管理员</option>
                        <option value="2" selected>教务处管理员</option>
                        <option value="3">教师</option>
                        <option value="4">学生</option>
                    </c:if>
                    <c:if test="${condition.roleId[0] == 3}">
                        <option value="">---请选择---</option>
                        <option value="1">系统管理员</option>
                        <option value="2">教务处管理员</option>
                        <option value="3" selected>教师</option>
                        <option value="4">学生</option>
                    </c:if>
                    <c:if test="${condition.roleId[0] == 4}">
                        <option value="">---请选择---</option>
                        <option value="1">系统管理员</option>
                        <option value="2">教务处管理员</option>
                        <option value="3">教师</option>
                        <option value="4" selected>学生</option>
                    </c:if>
                </select>
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>


    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/addUser.jsp">添加用户</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

    </div>

    <form id="form" action="${pageContext.request.contextPath}/DelSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>用户号</th>
                <th>账号</th>
                <th>角色</th>
                <th>姓名</th>
                <th>性别</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.uid}"></td>
                    <td>${s.count}</td>
                    <td>${user.uid}</td>
                    <td>${user.account}</td>
                    <td>
                        <c:if test="${user.roleId ==1}">
                            系统管理员
                        </c:if>
                        <c:if test="${user.roleId ==2}">
                            教务处管理员
                        </c:if>
                        <c:if test="${user.roleId ==3}">
                            教师
                        </c:if>
                        <c:if test="${user.roleId ==4}">
                            学生
                        </c:if>
                    </td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/FindUserServlet?uid=${user.uid}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.uid});">删除</a></td>
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
                        <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${condition.name[0]}&uid=${condition.uid[0]}&roleId=${condition.roleId[0]}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&uid=${condition.uid[0]}&roleId=${condition.roleId[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&uid=${condition.uid[0]}&roleId=${condition.roleId[0]}">${i}</a>
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
                        <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${condition.name[0]}&uid=${condition.uid[0]}&roleId=${condition.roleId[0]}"
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
