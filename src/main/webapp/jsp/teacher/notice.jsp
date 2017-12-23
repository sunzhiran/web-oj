<%--
  Created by IntelliJ IDEA.
  User: zhiran.sun
  Date: 2017/5/9
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.sdkd.utils.DateUtil" %>
<%@ page import="com.sdkd.pojo.Notice" %>
<%@ page import="com.sdkd.pojo.Teacher" %>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<%
    String path= request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线智能作业提交系统</title>
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
    Teacher user = (Teacher) session.getAttribute("teacher");
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
        <h1>欢迎,<%=user.getTeacherName()%></h1>
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
            <li><a href="/web-oj/jsp/teacher/index.jsp"><i class="fa fa-home"></i>首页</a></li>
            <li><a href="<%=basePath%>showAllHomeworksInTeacher?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>作业管理</a></li>
            <li class="active"><a href="<%=basePath%>selectAllNoticesInTeacher?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>通知管理</a></li>
            <li><a href="/web-oj/jsp/teacher/setup.jsp"><i class="fa fa-cog"></i>设置</a></li>
            <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
    </div><!--/.navbar-collapse -->
    <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
            <h1>通知列表</h1>

            <div class="row">
                <div class="col-md-12">
                    <div class="btn-group pull-right" id="templatemo_sort_btn">
                    </div>
                    <div class="table-responsive">
                        <ul class="nav nav-pills">
                            <li><a href="/web-oj/jsp/teacher/notice-add.jsp">添加通知 </a></li>
                        </ul>
                        <%
                            List<Notice> allNotices = (List<Notice>) request.getAttribute("allNotices");

                            if(allNotices==null||allNotices.size()==0){
                        %>
                        <h2>无通知</h2>
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
                            int numOfPage=allNotices.size()/numOfUserOnePage;
                            numOfPage = allNotices.size()%numOfUserOnePage==0 ? numOfPage:numOfPage+1;
                            int currentPage=Integer.parseInt(paramCurrentPage);
                            currentPage = currentPage<0?(numOfPage-1):currentPage;
                            currentPage = currentPage>=numOfPage?0:currentPage;
                            int start= currentPage*numOfUserOnePage;
                            int end = start+numOfUserOnePage;
                            end = end>allNotices.size()? allNotices.size():end;
                        %>
                        <div class="row">
                            <div class="col-md-11">
                                <div class="templatemo-progress">
                                    <div class="list-group">
                                        <%
                                            for(int i=start;i<end;i++){
                                                String time="";
                                                if(allNotices.get(i).getNoticeTime() != null && !allNotices.get(i).getNoticeTime().equals("")) {
                                                    time = DateUtil.formatDate(allNotices.get(i).getNoticeTime());
                                                }
                                                String writerName ="";
                                                if(null != allNotices.get(i).getNoticeWriterName() ){
                                                    writerName = allNotices.get(i).getNoticeWriterName();
                                                }
                                        %>
                                        <div class="list-group-item ">
                                            <div class="notice-time">
                                                <h4 class="list-group-item-heading">#<%=i+1%> <%=writerName%> </h4>
                                                <h5 ><%=time%></h5>
                                            </div>
                                            <p class="list-group-item-text"><%=allNotices.get(i).getNoticeContent()%></p>
                                        </div>
                                        <%
                                            }
                                        %>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <ul class="pagination pull-right">
                        <li><a href="<%=basePath%>selectAllNoticesInTeacher?currentPage= 0">首页</a></li>
                        <li ><a href="<%=basePath%>selectAllNoticesInTeacher?currentPage=<%= currentPage-1 %>">&laquo;</a></li>

                        <li ><a href="<%=basePath%>selectAllNoticesInTeacher?currentPage=<%=currentPage %>"><%=currentPage+1 %> <span class="sr-only">(current)</span></a></li>
                        <%--<li class="active"><a href="<%=basePath%>showAllHomeworksInTeacher?currentPage=<%=i-1 %>"><%=i %> <span class="sr-only">(current)</span></a></li>--%>

                        <li><a href="<%=basePath%>selectAllNoticesInTeacher?currentPage=<%= currentPage+1 %>">&raquo;</a></li>
                        <li><a href="<%=basePath%>selectAllNoticesInTeacher?currentPage=<%=numOfPage-1 %>">末页</a></li>
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
