<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/4
  Time: 11:35
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
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <br><br>
        <div class="panel panel-primary">
            <div class="panel panel-heading ">
               资料详情
            </div>
            <div class="panel-body">
                <c:if test="${not empty message}">
                    <div class="alert alert-info">
                            ${message}
                    </div>
                </c:if>
                <div class="page-header">
                    <h1>${student.stuName} <small>${student.stuAddress}</small></h1>
                </div>
                <h4 class="text-left">${student.stuAge}岁</h4>
                <h4>${student.studentClass.className}</h4>
            </div>
            <div class="panel-footer">
                <a class="btn btn-primary" href="/student/${student.id}/edit">修改</a>
                <a class="btn btn-default" href="/student">返回</a>
            </div>
        </div>

    </div>
</body>
</html>
