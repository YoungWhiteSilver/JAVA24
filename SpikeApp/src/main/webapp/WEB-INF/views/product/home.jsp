<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>主页</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <style>
        .product_list {
            border-bottom: 1px dashed #ccc;
            padding: 25px 0px;
        }
        .product_list:last-child {
            border-bottom: none;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/product">
                    <i class="fa fa-shopping-basket"></i> ProductStore
                </a>
            </div>
            <a href="/product/new" class="btn btn-success navbar-btn pull-right"><i class="fa fa-plus"></i> 添加商品</a>
        </div>
    </nav>
    <c:if test="${empty productList}" >
        <div class="container">
            <div class="well well-lg">
                <h1 class="text-center">还没有秒杀活动哦！</h1>
                <p class="text-center"><strong>点我去主页&nbsp;(●'◡'●)</strong></p>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty productList}">
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-body">
                    <c:forEach items="${productList}" var="product">
                        <div class="row product_list">
                            <div class="col-md-2">
                                <img src="http://ozoybvszl.bkt.clouddn.com/${product.productImage}?imageView2/1/w/150/h/150" alt="">
                            </div>
                            <div class="col-md-10">
                                <h3><a href="/product/${product.id}">${product.productName}</a> </h3>
                                <h4 style="margin-top: 25px" class="text-danger">抢购价: ￥${product.productPrice}</h4>
                                <c:choose>
                                    <c:when test="${not product.start}">
                                        <h4 style="margin-top: 35px">开始时间：<fmt:formatDate value="${product.startTime}" pattern="YYYY-MM-dd HH:mm"/></h4>
                                    </c:when>
                                    <c:when test="${product.end}">
                                        <h4 style="margin-top: 35px">已经结束</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <h4 style="margin-top: 35px">正在抢购</h4>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>

<script>
    $(function () {
        
    })
</script>
</body>

</html>