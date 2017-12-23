<%--
  Created by IntelliJ IDEA.
  User: zhiran.sun
  Date: 2017/5/10
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sdkd.pojo.User" %>
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
            <h1>上传作业</h1>
            <%
                String homeworkId = request.getParameter("homeworkId");
            %>
            <div class="row">
                <div class="col-md-12">
                    <form role="form" id="templatemo-preferences-form" method="post" action="<%=basePath %>uploadHomework" enctype="multipart/form-data" >
                        <div class="row">
                            <div class="col-md-6 margin-bottom-15">
                                <label for="homeworkId" class="control-label">作业ID</label>
                                <input type="text" class="form-control" id="homeworkId" name="homeworkId" value="<%=homeworkId%>" readonly="readonly">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12 margin-bottom-30">
                                <label for="paper">添加附件-课程设计报告</label>
                                <input type="file" id="paper" name="paper" >
                                <%--<p class="help-block">You can upload file here.</p>--%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 margin-bottom-30">
                                <label for="code">添加附件-源码(zip压缩包)</label>
                                <input type="file" id="code" name="code" >
                                <%--<p class="help-block">You can upload file here.</p>--%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 margin-bottom-30">
                                <label for="project">添加附件-编译后的文件(文件名为课程名全拼+学号)</label>
                                <input type="file" id="project" name="project" >
                                <%--<p class="help-block">You can upload file here.</p>--%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 margin-bottom-30">
                                <label for="sql">添加附件-sql文件</label>
                                <input type="file" id="sql" name="sql" >
                                <%--<p class="help-block">You can upload file here.</p>--%>
                            </div>
                        </div>
                        <div class="row templatemo-form-buttons">
                            <div class="col-md-12">
                                <button type="button" class="btn btn-primary" onclick="checkHWUp();">添加</button>
                                <button type="reset" class="btn btn-default">重置</button>
                            </div>
                        </div>
                        <span id="tixing"></span><span id="span_mesg"></span>
                    </form>
                    <div>
                    </div>
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

    function checkHWUp() {

        //TODO 表单检查
        var papervalue = document.getElementById("paper").value;
        if(papervalue == null || papervalue.length == 0 || papervalue == "") {
            alert("请选择课程设计报告。");
            return false;
        }

        var codevalue = document.getElementById("code").value;
        if(codevalue == null || codevalue.length == 0 || codevalue == ""){
            alert("请选择源码");
            return false;
        }

        var projectvalue = document.getElementById("project").value;
        if(projectvalue == null || projectvalue.length == 0 || projectvalue ==""){
            alert("请选择编译后的文件");
            return false;
        }

        var sqlvalue = document.getElementById("sql").value;
        if(sqlvalue == null || sqlvalue.length == 0 || sqlvalue ==""){
            alert("请选择sql文件");
            return false;
        }

        var upform = document.getElementById("templatemo-preferences-form");
        upform.submit();

        document.getElementById("tixing").innerText = "正在上传附件并部署项目和数据库，请耐心等待。。。";

//        var msg=document.getElementById("span_mesg");
//        var count=0;
//        var clear_=0;
//        var pints_interval;
//        //触发定时器
//        window.onload=function(){
//            pints_interval = setInterval(repeat,5000);
//        }
//
//        function repeat(){
//            if(count<6)
//            {
//                count++;
//                clear_++;
//                msg.innerHTML+="*";
//            }
//            else
//            {
//                msg.innerHTML="";
//                count=0;
//            }
//            if(clear_ == 20){
//                if (pints_interval) {
//                    clearInterval(pints_interval);//清除定时器
//                    pints_interval = null;
//                }
//            }
//        }
    }

</script>


</body>

</html>

