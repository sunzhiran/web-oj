<%--
  Created by IntelliJ IDEA.
  User: zhiran.sun
  Date: 2017/5/15
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sdkd.pojo.Manager" %>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<%
    String path= request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>在线智能作业管理系统</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="/web-oj/css/templatemo_main.css">
    <script src="/web-oj/js/jquery.min.js"></script>
    <script src="/web-oj/js/bootstrap.min.js"></script>
    <script src="/web-oj/js/templatemo_script.js"></script>
</head>
<body>
<%
    Manager manager = (Manager) session.getAttribute("manager");
%>
<div class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <div class="logo">
            <h1>在线智能作业管理系统</h1>
        </div>
    </div>
    <div class="login-name">
        <%
            if(manager != null){
        %>
        <h1>欢迎,<%=manager.getManagerName()%></h1>
        <%
        }
        else{
        %>
        <a href="/web-oj/jsp/login.jsp"><h1>登录</h1></a>
        <%
            }
        %>
    </div>
</div>
<div class="template-page-wrapper">
    <div class="navbar-collapse collapse templatemo-sidebar">
        <ul class="templatemo-sidebar-menu">
            <li>
                <form class="navbar-form">
                    <input type="text" class="form-control" id="templatemo_search_box" placeholder="Search...">
                    <span class="btn btn-default">Go</span>
                </form>
            </li>
            <li><a href="/web-oj/jsp/manager/index.jsp"><i class="fa fa-home"></i>首页</a></li>
            <li><a href="<%=basePath%>selectAllUsers?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>学生管理</a></li>
            <li><a href="<%=basePath%>showAllTeachers?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>教师管理</a></li>
            <li class="active"><a href="<%=basePath%>selectAllClasses?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>学生管理</a></li>
            <li><a href="/web-oj/jsp/manager/setup.jsp"><i class="fa fa-cog"></i>设置</a></li>
            <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
    </div><!--/.navbar-collapse -->
    <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
            <h1>添加专业</h1>
            <div class="row">
                <div class="col-md-12">
                    <form role="form" id="templatemo-preferences-form" onsubmit="return checkClassAdd();" method="post" action="<%=basePath %>addClass">
                        <div class="row">
                            <div class="col-md-6 margin-bottom-15">
                                <label for="userId" class="control-label">专业号</label>
                                <input type="text" class="form-control" id="userId" name="userId" >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 margin-bottom-15">
                                <label for="username" class="control-label">专业名</label>
                                <input type="text" class="form-control" id="username" name="username" >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 margin-bottom-15">
                                <label for="grade" class="control-label">年级</label>
                                <input type="text" class="form-control" id="grade" name="grade" >
                            </div>
                        </div>
                        <div class="row templatemo-form-buttons">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary">添加</button>
                                <button type="reset" class="btn btn-default">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <footer class="templatemo-footer">
        <div class="templatemo-copyright">
            <p>Copyright &copy; 2017 sunzhiran,山东科技大学</p>
        </div>
    </footer>

    <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">确定退出？</h4>
                </div>
                <div class="modal-footer">
                    <a href="/web-oj/jsp/login.jsp" class="btn btn-primary">是</a>
                    <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">

    function checkClassAdd() {

        //TODO 表单检查
        var username = document.getElementById("username").value;
        if(username == null || username.length == 0 || username == "") {
            alert("请输入专业名");
            return false;
        }

        var userId = document.getElementById("userId").value;
        if(userId == null || userId.length == 0 || userId == ""){
            alert("请输入专业号");
            return false;
        }

        var grade = document.getElementById("grade").value;
        if(grade == null || grade.length == 0 || grade ==""){
            alert("请输入年级");
            return false;
        }

        return true;
      /*  var upform = document.getElementById("templatemo-preferences-form");
        upform.submit();*/

    }

</script>
</body>
</html>
