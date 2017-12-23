<%--
  Created by IntelliJ IDEA.
  User: zhiran.sun
  Date: 2017/4/27
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sdkd.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sdkd.pojo.Homework" %>
<%@ page import="com.sdkd.utils.DateUtil" %>
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
    User user = (User) session.getAttribute("user");
%>
<div class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <div class="logo">
            <h1>在线智能作业提交系统</h1>
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
            <li class="active"><a href="<%=basePath%>showAllHomeworks?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>作业</a></li>
            <li><a href="<%=basePath%>selectAllNotices?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>通知</a></li>
            <li><a href="<%=basePath%>myHomework"><i class="fa fa-map-marker"></i>我的提交</a></li>
            <li><a href="/web-oj/jsp/setup.jsp"><i class="fa fa-cog"></i>设置</a></li>
            <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
    </div><!--/.navbar-collapse -->
    <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
            <h1>作业列表</h1>

            <div class="row">
                <div class="col-md-12">
                    <div class="btn-group pull-right" id="templatemo_sort_btn">
                    </div>
                    <div class="table-responsive">
                        <%
                            List<Homework> allHomeworks = (List<Homework>) request.getAttribute("allHomeworks");

                            if(allHomeworks==null||allHomeworks.size()==0){
                        %>
                        <h2>无作业</h2>
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
                            int numOfPage=allHomeworks.size()/numOfUserOnePage;
                            numOfPage = allHomeworks.size()%numOfUserOnePage==0 ? numOfPage:numOfPage+1;
                            int currentPage=Integer.parseInt(paramCurrentPage);
                            currentPage = currentPage<0?(numOfPage-1):currentPage;
                            currentPage = currentPage>=numOfPage?0:currentPage;
                            int start= currentPage*numOfUserOnePage;
                            int end = start+numOfUserOnePage;
                            end = end>allHomeworks.size()? allHomeworks.size():end;
                        %>
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>标题</th>
                                <th>附件</th>
                                <th>专业</th>
                                <th>截至时间</th>
                                <th>发布时间</th>
                                <th>发布者</th>
                                <th>编辑</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for(int i=start;i<end;i++){
                            %>
                            <tr>
                                <td ><%=i+1 %></td>
                                <td ><%=allHomeworks.get(i).getHomeworkTitle() %></td>
                                <%
                                    String attachment= "";
                                    String downPath = allHomeworks.get(i).getHomeworkAttachment();
                                    if(null != downPath){
                                        String temp[] = allHomeworks.get(i).getHomeworkAttachment().split("/");
                                        if (temp.length > 1) {
                                            attachment = temp[temp.length - 1];
                                        }
                                    }
                                %>
                                <td ><a href="download?fileName=<%=attachment%>&path=<%=downPath%>" target="_blank"/> <%= attachment%></td>
                                <td><%= allHomeworks.get(i).getHomeworkClassName()%></td>
                                <%
                                    String writeTime;
                                    String deadline;
                                    if(allHomeworks.get(i).getHomeworkTime() == null || allHomeworks.get(i).getHomeworkTime().equals("")){
                                        writeTime ="";
                                    }
                                    else
                                        writeTime = DateUtil.formatDate(allHomeworks.get(i).getHomeworkTime());

                                    if(allHomeworks.get(i).getHomeworkDeadline() == null || allHomeworks.get(i).getHomeworkDeadline().equals("")){
                                        deadline ="";
                                    }
                                    else
                                        deadline = DateUtil.formatDate(allHomeworks.get(i).getHomeworkDeadline());


                                    //String deadline = DateUtil.formatDate(allHomeworks.get(i).getHomeworkDeadline());
                                %>
                                <td ><%= deadline %></td>
                                <td ><%= writeTime %></td>
                                <td ><%= allHomeworks.get(i).getHomeworkWriterName()%></td>
                                <td><a href="javascript:uploadHomework(<%= allHomeworks.get(i).getHomeworkId() %>)" class="btn btn-default">上传</a></td>

                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <ul class="pagination pull-right">
                        <li><a href="<%=basePath%>showAllHomeworks?currentPage= 0">首页</a></li>
                        <li ><a href="<%=basePath%>showAllHomeworks?currentPage=<%= currentPage-1 %>">&laquo;</a></li>
                        <li ><a href="<%=basePath%>showAllHomeworks?currentPage=<%=currentPage %>"><%=currentPage+1 %> <span class="sr-only">(current)</span></a></li>
                        <li><a href="<%=basePath%>showAllHomeworks?currentPage=<%= currentPage+1 %>">&raquo;</a></li>
                        <li><a href="<%=basePath%>showAllHomeworks?currentPage=<%=numOfPage-1 %>">末页</a></li>
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
