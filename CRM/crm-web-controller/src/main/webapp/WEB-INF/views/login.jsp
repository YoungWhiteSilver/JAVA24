<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/6
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <title>凯盛软件CRM</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="include/css.jsp" %>
    <style>
      

    </style>

</head>
<body class="hold-transition login-page" id="loginBody">
<div class="login-box">
    <div class="login-logo">
       <img src="/static/dist/img/apahce.jpg" class="img-circle" style="width: 50px; height: 52px; "> &nbsp;<b>凯盛软件</b>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <form method="post" id="loginForm">
            <div class="form-group has-feedback">
                <input id="userName" name="moblie" type="text" class="form-control" placeholder="Email and TEL" value="123">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="password" name="password" type="password" class="form-control" placeholder="Password" value="admin">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
        </form>
            <!-- /.col -->
            <div >
                <button type="button" class="btn btn-primary btn-block btn-flat" id="loginBtn">登录</button>
            </div>
            <!-- /.col -->
        </div>

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/jquery.validate.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>

<script>
    $(function() {

        $("#loginBtn").click(function () {
            $("#loginForm").submit();
        });

        $(document).keydown(function(event) {
            if(event.keyCode == 13) {
                $("#loginForm").submit();
            }
        });

        $("#loginForm").validate({

            errorClass: "text-danger",
            errorElement: "span",

            rules : {
                moblie : {
                    required : true
                },
                password : {
                    required : true
                }
            },

            messages : {
                moblie : {
                    required : "请输入账号"
                },
                password : {
                    required : "请输入密码"
                }
            },

            submitHandler : function (form) {

                $.ajax({

                    url : "/",
                    type : "post",
                    data : $(form).serialize(),

                    beforeSend : function() {
                        $('#loginBtn').text("正在验证").attr("disabled", "disabled");
                    },

                    success : function(json) {
                        if(json.state == "success") {
                            window.location.href = "/employee";
                        } else {
                            layer.msg(json.message);
                        }
                    },
                    error : function () {
                        layer.alert("系统故障，正在尽力解决");
                    },
                    complete : function () {
                        $("#loginBtn").text("登录").removeAttr("disabled");
                    }
                })
            }
        });
    });

</script>
</body>
</html>
