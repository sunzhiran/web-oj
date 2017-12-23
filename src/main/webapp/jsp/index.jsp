<%@ page import="com.sdkd.pojo.User" %>
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
    User user = (User) session.getAttribute("user");
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
            <li class="active"><a href="/web-oj/jsp/index.jsp"><i class="fa fa-home"></i>首页</a></li>
            <li><a href="<%=basePath%>showAllHomeworks?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>作业</a></li>
            <li><a href="<%=basePath%>selectAllNotices?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>通知</a></li>
            <li><a href="<%=basePath%>myHomework"><i class="fa fa-map-marker"></i>我的提交</a></li>
            <li><a href="/web-oj/jsp/setup.jsp"><i class="fa fa-cog"></i>设置</a></li>
            <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
    </div><!--/.navbar-collapse -->
    <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
            <h1>各位同学：</h1>
            <p><h1>
            本系统可作为高校计算机相关专业web相关课程的学习使用<br>
            主要可实现课程作业的提交和自动部署，方便老师检查作业；
            并提供了相关信息的管理功能。
            <br>
            如对本系统有任何建议，可邮箱web@stust.com
        </h1></p>

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
</body>
</html>
