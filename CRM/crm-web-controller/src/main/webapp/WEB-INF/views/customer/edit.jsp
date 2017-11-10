<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/9
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@include file="../include/css.jsp" %>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp" %>

    <!-- =============================================== -->

    <jsp:include page="../include/left-meau.jsp">
        <jsp:param name="menu" value="customer_my" />
    </jsp:include>

    <!-- =============================================== -->

        <div class="content-wrapper">
    <div class="panel ">
        <div class="panel-heading">客户信息修改</div>
        <div class="panel-body">

            <form id="addCustomerForm" action="/customer/edit" method="post">
            <div class="input-group margin-bottom-sm">
                <span class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></span>
                <input type="text" class="form-control" name="custName" value="${customer.custName}">
            </div>
            <br>
            <div class="input-group margin-bottom-sm">
                <span class="input-group-addon"><i class="fa  fa-briefcase fa-lg"></i></span>
                <input type="text" class="form-control" name="jobTitle" value="${customer.jobTitle}">
            </div>
            <br>
            <div class="input-group margin-bottom-sm">
                <span class="input-group-addon"><i class="fa  fa-users fa-lg"></i></span>
                <input type="text" class="form-control" name="trade" value="${customer.trade}">
            </div>
            <br>
            <div class="input-group margin-bottom-sm">
                <span class="input-group-addon"><i class="fa fa-smile-o fa-lg"></i></span>
                <input type="text" class="form-control" name="source" value="${customer.source}">
            </div>
            <br>
            <div class="input-group margin-bottom-sm">
                <span class="input-group-addon"><i class="fa fa-phone-square fa-lg"></i></span>
                <input type="text" class="form-control" name="mobile" value="${customer.mobile}">
            </div>
            <br>
            <div class="input-group margin-bottom-sm">
                <span class="input-group-addon"><i class="fa  fa-location-arrow fa-lg"></i></span>
                <input type="text" class="form-control" name="address" value="${customer.address}">
            </div>
            <br>
            <div class="input-group margin-bottom-sm">
                <span class="input-group-addon"><i class="fa  fa-star fa-lg"></i></span>
                <select name="level" class="form-control">
                    <option value="">--选择星级--</option>
                    <option ${customer.level == '★' ? 'selected' : ''} value="★">★</option>
                    <option ${customer.level == '★★' ? 'selected' : ''} value="★★">★★</option>
                    <option ${customer.level == '★★★' ? 'selected' : ''} value="★★★">★★★</option>
                    <option ${customer.level == '★★★★' ? 'selected' : ''} value="★★★★">★★★★</option>
                    <option ${customer.level == '★★★★★' ? 'selected' : ''} value="★★★★★">★★★★★</option>
                </select>
            </div>
            <br>
            <div class="from-group">
                <textarea name="mark" id="editor" placeholder="备注">${customer.mark}</textarea>
            </div>
            <br>
            <input type="text" class="hidden" name="accountId" value="${sessionScope.curr_account.id}">
              <input type="text" class="hidden" name="id" value="${customer.id}">
            </form>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary pull-left" id="addCustomerFormBtn">保存</button>
            <a href="/customer/detail?id=${customer.id}" type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</a>
        </div>
    </div>
        </div>
    <%@include file="../include/footer.jsp" %>

</div>
<%@include file="../include/js.jsp" %>
<script>
    $(function () {

        var editor = new Simditor({
            textarea: $('#editor'),
            toolbar: [
                'bold',
                'color',
            ]
        });

        $("#addCustomerFormBtn").click(function () {
            $("#addCustomerForm").submit();
        });

        $("#addCustomerForm").validate({

            errorClass : "text-danger",
            errorElement : "span",

            rules : {
                custName : {
                    required : true
                },
                jobTitle : {
                    required : true
                },
                mobile : {
                    required : true
                },
                level : {
                    required : true
                }
            },

            messages : {
                custName : {
                    required : "请输客户名称"
                },
                jobTitle : {
                    required : "请输入客户职业"
                },
                mobile : {
                    required : "请输入手机号"
                },
                level : {
                    required : "请选择星级"
                }
            },

        });


    });
</script>
</body>
</html>
