<%--
  Created by IntelliJ IDEA.
  User: zhiran.sun
  Date: 2017/5/4
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sdkd.pojo.Homework" %>
<%@ page import="com.sdkd.utils.DateUtil" %>
<%@ page import="com.sdkd.pojo.Teacher" %>
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
    <script type="text/javascript" src="/web-oj/bin/laydate/laydate.js"></script>
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
            <h1>修改作业</h1>
            <%
                Homework homework = (Homework)request.getAttribute("homework");

            %>
            <div class="row">
                <div class="col-md-12">
                    <form role="form" id="templatemo-preferences-form" onsubmit="return checkHwEdit();" method="post" action="<%=basePath %>updateHomework" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-6 margin-bottom-15">
                                <label for="homeworkId" class="control-label">ID</label>
                                <input type="text" class="form-control" id="homeworkId" name="homeworkId" value=<%=homework.getHomeworkId()%> readonly="readonly" >
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 margin-bottom-15">
                                <label for="title" class="control-label">标题</label>
                                <input type="text" class="form-control" id="title" name="title" value=<%=homework.getHomeworkTitle()%> readonly="readonly">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 margin-bottom-15">
                                <label for="chooseClass" class="control-label">专业</label>
                                <input type="text" class="form-control" id="chooseClass" name="title" value=<%=homework.getHomeworkClass()%> readonly="readonly">
                            </div>
                        </div>
                        <%
                            String deadlineTime;
                            if(homework.getHomeworkDeadline() == null || homework.getHomeworkDeadline().equals("")){
                                deadlineTime ="";
                            }
                            else
                                deadlineTime = DateUtil.formatDate(homework.getHomeworkDeadline());
                        %>
                        <div class="row">
                            <div class="col-md-6 margin-bottom-15">
                                <label for="title" class="control-label">截止时间(要重新添加哟)</label>
                                <%--<input type="text" class="form-control" id="deadline" name="title" value="yyyy-MM-dd HH:mm:ss">--%>
                                <div class="inline layinput">
                                    <input placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" name="deadline" id="deadline">
                                    <label class="laydate-icon"></label>
                                </div>
                                <p class="help-block">原截至时间 <%=homework.getHomeworkDeadline()%></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 margin-bottom-30">
                                <label for="exampleInputFile">附件</label>
                                <input type="file" id="exampleInputFile" name="attachmentFile" >
                                <p class="help-block">原附件 <%=homework.getHomeworkAttachment()%></p>
                            </div>
                        </div>

                        <div class="row templatemo-form-buttons">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary" >修改</button>
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
<script>

    function checkHwEdit() {

        //TODO 表单检查

        var deadline = document.getElementById("deadline").value;
        if(deadline == null || deadline.length == 0 || deadline == ""){
            alert("请输入截至时间");
            return false;
        }
        /*var title = document.getElementById("title").value;
        if(title == null || title.length == 0|| title==""){
            alert("请输入标题");
            return false;
        }*/
        return true;

       /* var upform = document.getElementById("templatemo-preferences-form");
        upform.submit();*/

    }

</script>
</body>
</html>
