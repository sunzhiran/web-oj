<%--
  Created by IntelliJ IDEA.
  User: zhiran.sun
  Date: 2017/4/24
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sdkd.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<!-- saved from url=(0032)http://csit.dhu.edu.cn/shm/login -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>在线智能作业管理系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/web-oj/bin/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/web-oj/bin/bootstrap/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="/web-oj/css/main.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/web-oj/bin/bootstrap/fonts/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/web-oj/bin/bootstrap/fonts/ionicons.min.css">
    <link rel="stylesheet" href="/web-oj/css/application.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <!--<script type="text/javascript" src="./common/js/html5shiv.min.js"></script>-->
        <!--<script type="text/javascript" src="./common/js/respond.min.js"></script>-->
    <![endif]-->
    <script type="text/javascript" src="/web-oj/bin/jquery/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/web-oj/bin/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/web-oj/bin/bootstrap/js/bootbox-4.4.0.min.js"></script>
    <script src="/web-oj/bin/bootstrap/js/bootstrap-select.min.js"></script>
    
</head>
<body class="login-page modal-open">
<div class="modal fade in" id="loginModal" style="display: block;">
    <form action="<%=basePath%>login" id="loginForm" onsubmit="return checkLogin();" method="post" class="form-horizontal" role="form">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">在线智能作业管理系统</h4>
                </div>
                <div class="modal-body">
                    
                    <div class="form-group">
                        <label for="userId" class="col-sm-3 control-label">帐号<i class="fa fa-user"></i> </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="userId" id="userId" placeholder="userId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">密码 <i class="fa fa-lock"></i> </label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="usertype" class="col-sm-3 control-label">类型 <i class="fa fa-list-ul"></i> </label>
                        <div class="col-sm-8">
                            <select name="usertype" id="usertype" class="selectpicker">
                                <option value="1" selected>学生</option>
                                <option value="2">教师</option>
                                <option value="3">管理员</option>
                            </select>

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8">
                            
                            <button type="submit" class="btn btn-primary" >登陆系统</button>
                        </div>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </form>
</div><!-- /.modal -->
<script>
    $('#loginModal').modal({
        show:true,
        backdrop: 'static'
    })
</script><div class="modal-backdrop fade in"></div>
<script>

    function checkLogin() {

        //TODO 表单检查
        var userId = document.getElementById("userId").value;
        if(userId == null || userId.length == 0 || userId == "") {
            alert("请输入账号");
            return false;
        }

        var password = document.getElementById("password").value;
        if(password == null || password.length == 0 || password == ""){
            alert("请输入密码");
            return false;
        }

        return true;
       /* var upform = document.getElementById("loginForm");
        upform.submit();*/

    }

</script>
</body></html>