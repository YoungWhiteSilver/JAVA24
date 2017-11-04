<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/4
  Time: 16:17
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
            修改学生资料
        </div>
        <div class="panel-body">
            <form method="post" id="formSubmit">
                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" class="form-control" id="stuName" name="stuName">

                </div>
                <div class="form-group">
                    <label></label>年龄</label>
                    <input type="text" class="form-control" id="stuAge" name="stuAge">
                </div>
                <div class="form-group">
                    <label></label>地址</label>
                    <input type="text" class="form-control" id="stuAddress" name="stuAddress">
                </div>
                <div class="form-group">
                    <select name="stuClass" class="form-control">
                        <option value="">--选择班级--</option>
                        <c:forEach items="${studentClass}" var="studentClass">
                            <option value="${studentClass.id}">${studentClass.className}</option>
                        </c:forEach>
                    </select>
                </div>
            </form>
        </div>
        <div class="panel-footer">
            <button type="submit" id="btn" class="btn btn-primary">Submit</button>
            <a class="btn btn-default" href="/student">返回</a>
        </div>
    </div>
</div>
<script src="/static/js/jquery3.js"></script>

<script>
    $(function() {

        $("#btn").click(function () {

            $("#formSubmit").submit();

        });

    });
</script>
</body>
</html>
