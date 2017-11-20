<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/20
  Time: 16:42
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
<h3>UploadFile</h3>
<form action="http://upload-z1.qiniup.com" method="post" enctype="multipart/form-data">
    <input type="hidden" name="token" value="${upToken}">
    <input type="file" name="file">
    <input type="hidden" name="x:pid" value="1009" >
    <button>上传</button>
</form>
</body>
</html>