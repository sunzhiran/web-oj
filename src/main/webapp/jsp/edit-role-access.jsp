<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/12/21
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.sdkd.pojo.StuClass" %>
<%@ page import="com.sdkd.pojo.RBACUser" %>
<%@ page import="com.sdkd.pojo.RBACAccess" %>
<%@ page import="com.sdkd.pojo.RBACRoleAccess" %>
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
    RBACUser user = (RBACUser) session.getAttribute("user");
%>
<div class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <div class="logo">
            <h1>在线智能作业管理系统</h1>
        </div>
    </div>
    <div class="login-name">
        <%
            if(user != null){
        %>
        <h1>欢迎,<%=user.getUserName()%></h1>
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
            <li><a href="/web-oj/jsp/index.jsp"><i class="fa fa-home"></i>首页</a></li>
            <li><a href="<%=basePath%>showAllHomeworks?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>作业</a></li>
            <li><a href="<%=basePath%>selectAllNotices?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>通知</a></li>
            <li><a href="<%=basePath%>myHomework"><i class="fa fa-map-marker"></i>我的提交</a></li>
            <li><a href="/web-oj/jsp/setup.jsp"><i class="fa fa-cog"></i>设置</a></li>
            <li><a href="<%=basePath%>selectAllUsers?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>用户管理</a></li>
            <li class="active"><a href="<%=basePath%>selectAllRoles?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>角色管理</a></li>
            <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
    </div><!--/.navbar-collapse -->
    <div class="templatemo-content-wrapper">
        <div class="templatemo-content">

            <h1>为角色设置权限</h1>
            <%
                List<RBACAccess> allAccesses = (List<RBACAccess>) request.getAttribute("allAccesses");
                List<RBACRoleAccess> selectedRoleAccess =(List<RBACRoleAccess>) request.getAttribute("selectedRoleAccess");
            %>
            <div class="row">
                <div class="col-md-12">
                    <form role="form" id="templatemo-preferences-form" onsubmit="return checkUserAdd();" method="post" action="<%=basePath %>addRole">
                        <div class="row">
                            <div class="col-md-12 margin-bottom-15">

                                <%
                                    if (allAccesses != null) {
                                    for(int i=0;i<allAccesses.size();i++){
                                %>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox1" value="<%=allAccesses.get(i).getAccessId()%>" checked> <%=allAccesses.get(i).getAccessTitle()%>
                                </label>
                                <%
                                    }}
                                %>
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
<script>

    function checkUserAdd() {

        //TODO 表单检查
        var username = document.getElementById("username").value;
        if(username == null || username.length == 0 || username == "") {
            alert("请输入姓名");
            return false;
        }

        var userId = document.getElementById("userId").value;
        if(userId == null || userId.length == 0 || userId == ""){
            alert("请输入学号");
            return false;
        }

        var password = document.getElementById("password").value;
        if(password == null || password.length == 0 || password ==""){
            alert("请输入密码");
            return false;
        }

        var upform = document.getElementById("templatemo-preferences-form");
        upform.submit();

    }

</script>
</body>
</html>
