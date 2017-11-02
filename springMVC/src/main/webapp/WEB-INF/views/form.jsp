
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/2
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>

        <div class="container">

            <c:if test="${not empty message}">
                <div class="alert alert-success">
                        ${message}
                </div>
            </c:if>

            <form action="/form" method="post" enctype="multipart/form-data">
                <legend>账号注册</legend>
                <div class="form-group">
                    <label>账号</label>
                    <input type="text" name="userName" class="form-control">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="text" name="password" class="form-control">
                </div>
                <div class="form-group">
                    <label>电子邮件</label>
                    <input type="text" name="email" class="form-control">
                </div>
                <div class="form-group">
                    <label>照片</label>
                    <input type="file" name="photo" class="form-control">
                </div>
                <div class="form-group">
                    <button class="btn btn-primary">注册</button>
                </div>
            </form>
        </div>


</body>
</html>
