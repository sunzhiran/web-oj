<%--
  Created by IntelliJ IDEA.
  User: zhiran.sun
  Date: 2017/5/11
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sdkd.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sdkd.utils.DateUtil" %>
<%@ page import="com.sdkd.pojo.Teacher" %>
<%@ page import="com.sdkd.pojo.HwStu" %>
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
            <li class="active"><a href="<%=basePath%>showAllHomeworksInTeacher?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>作业管理</a></li>
            <li><a href="<%=basePath%>selectAllNoticesInTeacher?currentPage=<%=0 %>"><i class="fa fa-cubes"></i>通知管理</a></li>
            <li><a href="/web-oj/jsp/teacher/setup.jsp"><i class="fa fa-cog"></i>设置</a></li>
            <li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
        </ul>
    </div><!--/.navbar-collapse -->
    <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
            <h1>作业完成详情</h1>
            <h1>已提交</h1>
            <%
                List<HwStu> completedHomework = (List<HwStu>) request.getAttribute("completedHomework");

                if(completedHomework==null||completedHomework.size()==0){
            %>
            <h2><font color="red">无已提交作业</font></h2>
            <%
            }
            else{
                int start =0;
                int end = completedHomework.size();
                /*String paramCurrentPage=request.getParameter("currentPage");
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
                end = end>allHomeworks.size()? allHomeworks.size():end;*/
            %>
            <div class="row">
                <div class="col-md-12">
                    <div class="btn-group pull-right" id="templatemo_sort_btn">
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>报告</th>
                                <th>源码</th>
                                <th>sql文件</th>
                                <th>项目</th>
                                <th>发布时间</th>
                                <th>相似度</th>
                                <th>相似源</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for(int i=start;i<end;i++){
                            %>
                            <tr>
                                <td ><%=completedHomework.get(i).getStuId() %></td>
                                <td ><%=completedHomework.get(i).getStuName() %></td>
                                <%
                                    String paper= "";
                                    String downloadPaperPath = completedHomework.get(i).getPaperUrl();
                                    if(null != completedHomework.get(i).getPaperUrl()){
                                        String temp1[] = completedHomework.get(i).getPaperUrl().split("/");
                                        if (temp1.length > 1) {
                                            paper = temp1[temp1.length - 1];
                                        }
                                    }
                                %>
                                <td ><a href="download?fileName=<%=paper%>&path=<%=downloadPaperPath%>" target="_blank"/> <%= paper%></td>
                                <%
                                    String code= "";
                                    String downloadCodePath = completedHomework.get(i).getCodeUrl();
                                    if(null != completedHomework.get(i).getCodeUrl()){
                                        String temp2[] = completedHomework.get(i).getCodeUrl().split("/");
                                        if (temp2.length > 1) {
                                            code = temp2[temp2.length - 1];
                                        }
                                    }
                                %>
                                <td ><a href="download?fileName=<%=code%>&path=<%=downloadCodePath%>" target="_blank"/><%= code%></td>
                                <%
                                    String sql= "";
                                    String downloadSqlPath = completedHomework.get(i).getSqlUrl();
                                    if(null != downloadSqlPath){
                                        String temp4[] = completedHomework.get(i).getSqlUrl().split("/");
                                        if (temp4.length > 1) {
                                            sql = temp4[temp4.length - 1];
                                        }
                                    }
                                %>
                                <td ><a href="download?fileName=<%=sql%>&path=<%=downloadSqlPath%>" target="_blank"/><%= sql%></td>

                                <%
                                    String project= "";
                                    String projectUrl = completedHomework.get(i).getProjectUrl();
                                    if(null != completedHomework.get(i).getProjectUrl()){
                                        String temp3[] = completedHomework.get(i).getProjectUrl().split("/");
                                        if (temp3.length > 1) {
                                            project = temp3[temp3.length - 1];
                                        }
                                    }
                                %>
                                <td ><a href="<%=projectUrl%>" target="_blank"/><%= project%></td>
                                <%--<td><%= completedHomework.get(i).getUploadTime()%></td>--%>
                                <%
                                    String writeTime="";
                                    if(completedHomework.get(i).getUploadTime() != null){
                                        writeTime = DateUtil.formatDate(completedHomework.get(i).getUploadTime());
                                    }
                                %>
                                <td ><%= writeTime %></td>
                                <%
                                    double sim = completedHomework.get(i).getHwStuSim();
                                    boolean high = sim>90;
                                %>
                                <td >
                                    <%
                                        if(high){
                                    %>
                                    <font color="red"><%=sim%>%</font>
                                    <%
                                        }
                                        else{
                                    %>
                                    <%=sim%>%
                                    <%
                                        }
                                    %>
                                </td>
                                <%
                                    String simId ="";
                                    if(completedHomework.get(i).getHwStuSimId() !=null){
                                        simId = completedHomework.get(i).getHwStuSimId();
                                    }
                                %>
                                <td><%=simId%></td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%
                }
            %>

            <h1>未提交</h1>
            <%
                List<User> noCompletedUser = (List<User>) request.getAttribute("noCompletedUser");

                if(noCompletedUser==null||noCompletedUser.size()==0){
            %>
            <h2>都已完成</h2>
            <%
            }
            else{
                int start1 =0;
                int end1 = noCompletedUser.size();
            %>
            <div class="row">
                <div class="col-md-12">
                    <div class="btn-group pull-right" id="templatemo_sort_btn1">
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for(int i=start1;i<end1;i++){
                            %>
                            <tr>
                                <td ><%=noCompletedUser.get(i).getUserId() %></td>
                                <td ><%=noCompletedUser.get(i).getUserName() %></td>

                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%
                }
            %>
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