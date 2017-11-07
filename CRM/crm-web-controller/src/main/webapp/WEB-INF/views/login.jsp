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

</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="../../index2.html"><b>凯盛软件</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <form method="post" id="loginForm">
            <div class="form-group has-feedback">
                <input id="userName" name="userName" type="text" class="form-control" placeholder="Email and TEL">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="password" name="password" type="password" class="form-control" placeholder="Password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
        </form>
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox icheck">
                    <label>
                        <a href="#">忘记密码</a><br>
                    </label>
                </div>
            </div>
            <!-- /.col -->
            <div class="col-offset-8 col-xs-4">
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
                userName : {
                    required : true
                },
                password : {
                    required : true
                }
            },

            messages : {
                userName : {
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
                        $('loginBtn').text("正在验证").attr("disabled", "disabled");
                    },

                    success : function(json) {
                        if(json.state == "success") {
                            window.location.href = "/home";
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
