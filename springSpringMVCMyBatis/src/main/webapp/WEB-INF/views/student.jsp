<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/3
  Time: 17:33
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
</head>
<body>
<c:forEach items="${studentList}" var="student">
    <h1>${student.id}</h1>
    <h1>${student.stuName}</h1>
    <h1>${student.stuAge}</h1>
    <h1>${student.stuAddress}</h1>
    <h1>--------------------------</h1>
</c:forEach>

</body>
</html>
