<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/9
  Time: 15:25
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
    <style>
        .name-avatar {
            display: inline-block;
            width: 50px;
            height: 50px;
            background-color: #30a5cc;
            border-radius: 50%;
            text-align: center;
            line-height: 50px;
            font-size: 24px;
            color: #FFF;
        }
        .table>tbody>tr:hover {
            cursor: pointer;
        }
        .table>tbody>tr>td {
            vertical-align: middle;
        }
        .star {
            font-size: 20px;
            color: #ff7400;
        }
    </style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

<%@include file="../include/header.jsp" %>

    <!-- ===============★★★★★★★★★★★★★★★★================================ -->

<jsp:include page="../include/left-meau.jsp">
    <jsp:param name="menu" value="customer_my" />
</jsp:include>

    <!-- =============★★★★★★★★★★★★★★★★======★★★★★★★★★★★★=★★★★★★=★★★★★★========================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-handshake-o fa-lg"></i> <strong>公海客户</strong></h3>
                    <div class="box-tools pull-right">

                        <div class="btn-group">
                            <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-file-excel-o"></i> 导出Excel <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="/customer/my/export.xls" class=""><strong>导出为.xls文件</strong></a></li>
                                <li><a href="/customer/my/export.csv"><strong>导出为.csv文件</strong></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty param.warning}">
                    <div class="alert alert-info alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>提示：</strong> ${param.warning}
                    </div>
                </c:if>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <thead>

                        <tr class="info">
                            <th width="80"></th>
                            <th>姓名</th>
                            <th>职位</th>
                            <th>跟进时间</th>
                            <th>级别</th>
                            <th>联系方式</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="customer">
                            <tr class="customerId" rel="${customer.id}">
                                <td><span class="name-avatar">${customer.custName.charAt(0)}</span></td>
                                <td>${customer.custName}</td>
                                <td>${customer.jobTitle}</td>
                                <c:if test="${not empty customer.updateName}">
                                    <td class="time">${customer.updateName}</td>
                                </c:if>
                                <c:if test="${empty customer.updateName}">
                                    <td>暂无跟进记录</td>
                                </c:if>
                                <td class="star">${customer.level}</td>
                                <td><i class="fa fa-phone"></i> ${customer.mobile} <br></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
                <!-- /.box-body -->
            </div>
            <nav style="text-align: center">
                <ul id="pagination-demo" class="pagination-sm"></ul>
            </nav>
            <!-- /.box -->
        </section>
        <!-- /.content ★★★★★★★★ -->
    </div>
    <!-- /.content-wrapper  ★★★★★★★★★★ -->



    <%--添加账号模态框--%>
    <!-- Modal -->


<%@include file="../include/footer.jsp"  %>

</div>

<!-- ./wrapper -->
<%@include file="../include/js.jsp" %>
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>

<script>

    $(function() {

        /*===================================分页==============================*/
        $('#pagination-demo').twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePages: 10,
            href:"?p={{number}}",
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页'
        });


        /*================富========文===============本==========================*/


        /*=========================================格式化日期=====================================*/


        /*=========================================客户详情的请求======================================*/
        $(".customerId").click(function () {

            var id = $(this).attr("rel");
            window.location.href = "/customer/detail?id="+ id;

        });
        /*==============================================新增客户==========================================*/
        $("#newCustomrt").click(function () {
            $("#addCustormerModel").modal({
                show:true,
                backdrop:'static'
            });
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

            submitHandler : function (form) {

                $.ajax({

                    url : "/customer/new",
                    type : "post",
                    data :  $(form).serialize(),
                    beforeSend : function() {
                        $('#addCustomerFormBtn').text("正在录入").attr("disabled", "disabled");
                    },

                    success : function(json) {

                        if(json.state == "success") {

                            $("#addCustormerModel").modal('hide');
                            history.go(0);
                            layer.msg("保存成功");

                        } else {
                            layer.msg(json.message);
                        }
                    },
                    error : function () {
                        layer.msg("服务器跑了，正在追。。。。");
                    },
                    complete : function () {
                        $("#addCustomerFormBtn").text("保存").removeAttr("disabled");
                    }
                });
            }

        });
    });

</script>
</body>
</html>
