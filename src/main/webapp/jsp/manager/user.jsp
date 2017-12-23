<%--
  Created by IntelliJ IDEA.
  User: zhiran.sun
  Date: 2017/5/5
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sdkd.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sdkd.pojo.Manager" %>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<%
    String path= request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
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
    <script src="/web-oj/js/function-my.js"></script>
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
                    <button type="submit" class="btn btn-default">Go</button>
                </form>
            </li>
            <li><a href="/web-oj/jsp/manager/index.jsp"><i class="fa fa-home"></i>首页</a></li>
            <li class="active"><a href="<%=basePath%>selectAllUsers?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>学生管理</a></li>
            <li><a href="<%=basePath%>showAllTeachers?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>教师管理</a></li>
            <li><a href="<%=basePath%>selectAllClasses?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>专业管理</a></li>
            <li><a href="/web-oj/jsp/manager/setup.jsp"><i class="fa fa-cog"></i>设置</a></li>
            <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
    </div><!--/.navbar-collapse -->
    <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
            <h1>学生列表</h1>

            <div class="row">
                <div class="col-md-12">
                    <div class="btn-group pull-right" id="templatemo_sort_btn">
                        <form class="navbar-form" action="<%=basePath%>selectUserByPartUsername?currentPage=0" METHOD="post">
                            <input type="text" class="form-control" id="user_search_box" name="username" placeholder="学生姓名">
                            <button type="submit" class="btn btn-default">查询</button>
                        </form>
                    </div>
                    <div class="table-responsive">
                        <ul class="nav nav-pills">
                            <li><a href="<%=basePath%>selectClassAtAddUser">添加学生 </a></li>
                        </ul>
                        <%
                            List<User> allUsers = (List<User>) request.getAttribute("allUsers");

                            if(allUsers==null||allUsers.size()==0){
                        %>
                        <h2>无学生</h2>
                        <%
                        }
                        else{
                            String paramCurrentPage=request.getParameter("currentPage");
                            if (paramCurrentPage != null) {
                                paramCurrentPage = paramCurrentPage.trim();
                            }
                            else {
                                paramCurrentPage = 0+"";
                            }
                            int numOfUserOnePage=5;
                            int numOfPage=allUsers.size()/numOfUserOnePage;
                            numOfPage = allUsers.size()%numOfUserOnePage==0 ? numOfPage:numOfPage+1;
                            int currentPage=Integer.parseInt(paramCurrentPage);
                            currentPage = currentPage<0?(numOfPage-1):currentPage;
                            currentPage = currentPage>=numOfPage?0:currentPage;
                            int start= currentPage*numOfUserOnePage;
                            int end = start+numOfUserOnePage;
                            end = end>allUsers.size()? allUsers.size():end;
                        %>
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>专业</th>
                                <th>性别</th>
                                <th>备注</th>
                                <th>编辑</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for(int i=start;i<end;i++){
                                    String note = "";
                                    if(null != allUsers.get(i).getUserNote()){
                                        note = allUsers.get(i).getUserNote();
                                    }
                            %>
                            <tr>
                                <td ><%=allUsers.get(i).getUserId() %></td>
                                <td ><%=allUsers.get(i).getUserName() %></td>
                                <td ><%= allUsers.get(i).getUserClassName()%></td>
                                <td ><%= allUsers.get(i).getUserSex() %></td>
                                <td ><%= note%> </td>
                                <td><a href="javascript:editUser(<%= allUsers.get(i).getUserId() %>)" class="btn btn-default">编辑</a></td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <ul class="pagination pull-right">
                        <li><a href="<%=basePath%>selectAllUsers?currentPage= 0">首页</a></li>
                        <li ><a href="<%=basePath%>selectAllUsers?currentPage=<%= currentPage-1 %>">&laquo;</a></li>

                        <li ><a href="<%=basePath%>selectAllUsers?currentPage=<%=currentPage %>"><%=currentPage+1 %> <span class="sr-only">(current)</span></a></li>
                        <%--<li class="active"><a href="<%=basePath%>showAllHomeworksInTeacher?currentPage=<%=i-1 %>"><%=i %> <span class="sr-only">(current)</span></a></li>--%>

                        <li><a href="<%=basePath%>selectAllUsers?currentPage=<%= currentPage+1 %>">&raquo;</a></li>
                        <li><a href="<%=basePath%>selectAllUsers?currentPage=<%=numOfPage-1 %>">末页</a></li>
                    </ul>
                    <%
                        }
                    %>
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


</body>
</html>
