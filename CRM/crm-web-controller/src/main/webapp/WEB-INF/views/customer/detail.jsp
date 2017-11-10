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

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-id-card-o fa-lg" ></i> <strong>客户资料</strong></h3>
                    <div class="box-tools">
                        <a href="/customer/my" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回</a>
                        <a href="javascript:;" id="editCustomer" class="btn bg-purple btn-sm"><i class="fa fa-pencil"></i> 编辑</a>
                        <a class="btn bg-orange btn-sm"><i class="fa fa-exchange"></i> 转交</a>
                        <a class="btn bg-maroon btn-sm"><i class="fa fa-recycle"></i> 公海</a>
                        <a class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</a>
                    </div>
                </div>

                <c:if test="${not empty param.warning}">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>警告</strong> ${param.warning}
                    </div>
                </c:if>

                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title info">姓名</td>
                            <td>${customer.custName}</td>
                            <td class="td_title success">职位</td>
                            <td>${customer.jobTitle}</td>
                            <td class="td_title info">联系电话</td>
                            <td>${customer.mobile}</td>
                        </tr>
                        <tr>
                            <td class="td_title success">所属行业</td>
                            <td>${customer.trade}</td>
                            <td class="td_title info">客户来源</td>
                            <td>${customer.source}</td>


                            <td class="td_title success">级别</td>
                            <td>${customer.level}</td>
                        </tr>
                        <tr>
                            <td class="td_title info">地址</td>
                            <td colspan="5">${customer.address}</td>
                        </tr>
                        <tr>
                            <td class="td_title success">备注</td>
                            <td colspan="5">
                                <c:if test="${empty customer.mark}">
                                    <span style="color: #9a2427">这家伙很懒。。。没留备注</span>
                                </c:if>
                                ${customer.mark}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="box-footer">
                    <div style="color: #ccc" class="pull-right">
                        创建客户时间：<span id="createTime" title="${customer.createTime}">${customer.createTime}</span>
                        <c:if test="${not empty customer.updateName}">
                            最后跟进时间：<span style="color: #ccc" id="updataName" title="${customer.updateName}">${customer.updateName}</span>
                        </c:if>
                    </div>
                    <%--<span style="color: #ccc" class="pull-left" id="createTime"></span>--%>

                        <%--<c:if test="${not empty customer.updateName}">--%>
                            <%--<span style="color: #ccc"  class="pull-right" id="updataName">${customer.updateName}</span>--%>
                        <%--</c:if>--%>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">跟进记录</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">日程安排</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">相关资料</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

<%@include file="../include/footer.jsp" %>

</div>
<%@include file="../include/js.jsp" %>
<script>
    $(function () {
        /*=========================================格式化日期=========================================*/
        moment.locale("zh-cn");
        $("#createTime").text(moment($("#createTime").text()).format("HH:mm:ss"));
        $("#updataName").text(moment($("#updataName").text()).format("HH:mm:ss"));

        /*==========================================编辑功能===========================================*/

        var customerId = ${customer.id};

        $("#editCustomer").click(function () {

            window.location.href = "/customer/"+ customerId + "/edit";

        });


    });
</script>
</body>
</html>
