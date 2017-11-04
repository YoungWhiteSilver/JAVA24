<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/4
  Time: 10:59
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
                学生信息
            </div>
            <div class="panel-body">

                <c:if test="${not empty message}">
                    <div class="alert alert-info">
                            ${message}
                    </div>
                </c:if>
                <form class="form-inline">
                    <input type="text" name="stuName" placeholder="学生姓名" class="form-control" value="${param.stuName}">

                    <select name="stuClass" class="form-control">
                        <option value="">--选择班级--</option>
                        <c:forEach items="${studentClass}" var="studentClass">
                            <option value="${studentClass.id}" ${param.stuClass == studentClass.id ? 'selected' : ''}>${studentClass.className}</option>
                        </c:forEach>
                    </select>

                    <select name="stuAge" class="form-control">
                        <option value="">--选择年龄--</option>
                        <c:forEach items="${studentAgeList}" var="age">
                            <option value="${age}" ${param.stuAge == age ? 'selected' : ''}>${age}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-success">搜索</button>
                    <a href="/student" class="btn btn-default">重置</a>
                </form>

                <br>
                <table class="table  table-striped">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>年龄</th>
                        <th>班级</th>
                        <th>地址</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${studentPageInfo.list}" var="student">
                        <tr>
                            <td>${student.id}</td>
                            <td><a href="/student/${student.id}">${student.stuName}</a></td>
                            <td>${student.stuAge}</td>
                            <td>${student.stuClassName.className}</td>
                            <td>${student.stuAddress}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="panel-footer">
                <a href="/student/new" class="btn btn-primary">新增</a>
            </div>
        </div>
        <ul id="pagination-demo" class="pagination-sm"></ul>
    </div>

<script src="/static/js/jquery3.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>

<script>
    $(function(){

        $('#pagination-demo').twbsPagination({
            totalPages: ${studentPageInfo.pages},
            visiblePages: 10,
            href:"?p={{number}}&stuAge=${param.stuAge}&stuName="+ encodeURIComponent("${param.stuName}") + "&stuClass=" + encodeURIComponent("${param.stuClass}"),
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页'
        });

    });
</script>

</body>
</html>
