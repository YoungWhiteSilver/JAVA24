<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/13
  Time: 9:47
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
    <!-- Bootstrap 3.3.6 -->
    <%@include file="../include/css.jsp" %>
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

   <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../include/left-meau.jsp">
        <jsp:param name="menu" value="sale_chance" />
    </jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" id="addSaleChanceModalBtn">
                            <i class="fa fa-plus"></i> 添加机会
                        </button>
                    </div>
                </div>
                <c:if test="${not empty message}">
                    ${message}
                </c:if>
                <div class="box-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <td>机会名称</td>
                            <td>关联客户</td>
                            <td>机会价值</td>
                            <td>当前进度</td>
                            <td>最后跟进时间</td>
                            <td>
                                #
                            </td>
                        </tr>
                        </thead>
                        <tbody>

                            ${message}

                            <c:if test="${empty message}">
                                <c:forEach items="${saleChanceList}" var="saleChance">

                                        <tr class="saleChanceDetail" rel="${saleChance.id}">
                                            <td>${saleChance.name}</td>
                                            <td>${saleChance.customer.custName}</td>
                                            <td>${saleChance.worth}</td>

                                            <c:choose>
                                                <c:when test="${saleChance.progress == '暂时搁置'}">
                                                    <td class="danger">${saleChance.progress}</td>
                                                </c:when>
                                                <c:when test="${saleChance.progress == '成交'}">
                                                    <td class="success">${saleChance.progress}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>${saleChance.progress}</td>
                                                </c:otherwise>
                                         </c:choose>

                                        <td>${saleChance.lastTime}</td>
                                        <td>****</td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%--添加账号模态框--%>
    <!-- Modal -->
    <div class="modal fade" id="addSaleChanceModal" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">新增机会</h4>
                </div>
                <div class="modal-body">

                    <form id="addSaleChanceForm" method="post" action="/sale/my/new">

                        <div class="input-group margin-bottom-sm">
                            <span class="input-group-addon"><i class="fa fa-crosshairs fa-lg"></i></span>
                            <input type="text" class="form-control" name="name" placeholder="机会名称">
                        </div>
                        <br>
                        <div class="input-group margin-bottom-sm">
                            <span class="input-group-addon"><i class="fa  fa-briefcase fa-lg"></i></span>
                            <select name="custId" class="form-control">
                                <option value="">请选择关联客户</option>
                                <c:forEach items="${customerList}" var="customer">
                                    <option value="${customer.id}">${customer.custName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br>
                        <span id="worthSpan"></span>
                        <div class="input-group margin-bottom-sm">
                            <span class="input-group-addon"><i class="fa fa-money fa-lg"></i></span>
                            <input type="text" class="form-control" id="worth" name="worth" placeholder="机会价值">
                        </div>
                        <br>
                        <div class="input-group margin-bottom-sm">
                            <span class="input-group-addon"><i class="fa fa-spinner fa-lg"></i></span>
                            <select name="progress" class="form-control">
                                <option value="">当前进度</option>
                                <c:forEach items="${progressList}" var="progress">
                                    <option value="${progress}">${progress}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br>
                        <div class="from-group">
                            <textarea name="content" id="editor" placeholder="内容"></textarea>
                        </div>
                        <br>

                        <input type="hidden" class="hidden" name="accountId" value="${sessionScope.curr_account.id}">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary" id="addSaleChanceBtn">保存</button>
                </div>
            </div>
        </div>
    </div>
    <%--添加账号模态框结束--%>


    <!-- 底部 -->
<%@include file="../include/footer.jsp" %>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp" %>

<script>
    $(function () {

        /*===============================机会    详情=================================*/
        $(".saleChanceDetail").click(function () {

            var id = $(this).attr("rel");
            window.location.href = "/sale/my/detail?saleId="+ id;

        });

        /*===============================modal框 添加机会=================================*/

        var editor = new Simditor({
            textarea: $('#editor'),
            toolbar: [
                'bold',
                'color',
            ]
        });

        $("#addSaleChanceModalBtn").click(function () {
            $("#addSaleChanceModal").modal({
                show : true,
                backdrop:'static'
            });
        });

        $("#addSaleChanceBtn").click(function () {
            $("#addSaleChanceForm").submit();
        });

        $("#addSaleChanceForm").validate({
            errorClass : "text-danger",
            errorElement : "span",
            rules : {
                name : {
                    required : true
                },
                custId : {
                    required : true
                },
                worth : {
                    required : true,
                    number : true,
                    min : 1,
                    max : 999999999999
                }
            },
            messages : {
                name : {
                    required : "请输入机会名称"
                },
                custId : {
                    required : "请选择客户"
                },
                worth : {
                    required : "请输入价值",
                    number : "价值必须为数字",
                    min : "价值必须大于0",
                    max : "最大允许12位"
                }
            },

            submitHandler : function (form) {
                $.ajax({
                    url : "/sale/my/new",
                    type : "POST",
                    data : $(form).serialize(),
                    beforeSend : function () {
                        $("#addSaleChanceModalBtn").text("正在提交").attr("disabled","disabled");
                    },
                    success : function (json) {
                        if(json.state == "success") {
                            $("#addSaleChanceModal").modal('hide');
                            history.go(0);
                            layer.msg("保存成功");
                        } else {
                            layer.msg(json.message);
                        }
                    },
                    error : function () {
                        layer.msg("服务器跑了，正在追。。。。");
                    } ,
                    complete : function () {

                       $("#addSaleChanceModalBtn").text("保存").removeAttr("disabled");
                   }

                });
            }
        });
        /*===============================modal框 添加机会=======结束==========================*/

        /*===============================动态的获取输入价值的指并转换为中文表示=================================*/

        $("#worth").change(function () {
            var worth = $("#worth").val();
            var worthPoint = worth.split(".");
            var newWorth = worthPoint[0].split("");
            var j = 0;
            var newText = [];
            for(var i = newWorth.length - 1; i >= 0; i --) {
                //九万八千
                num  = chinese(newWorth[i]);

                if(j == 1 || (j % 4) == 1) {
                    newText.push("十");
                }

                if(j== 2 || (j % 4) == 2) {
                    newText.push("百");
                }

                if(j == 3 || (j % 4) == 3) {
                    newText.push("千");
                }

                if(j == 4 ) {
                    newText.push("万");
                }

                if(j == 8) {
                    newText.push("亿");
                }
                j ++;
//              newText.push(newWorth[i]);
                newText.push(num);
            }

            var zhWorthProint = [];
            if(worthPoint[1] != null) {
                var rightWorthPoint = worthPoint[1].split("");

                for(var i = rightWorthPoint.length - 1; i >= 0; i --) {
                    var num = chinese(rightWorthPoint[i]);
                    zhWorthProint.push(num);
                }
            } else {
                zhWorthProint.push("零");
            }

            if(newWorth.length <= 12) {
                $("#worthSpan").text(newText.reverse().join("") + "." + zhWorthProint.reverse().join("") + "（元）");
            }
        });

        function chinese(num) {
            if('1' == num) {
                num = '一';
            }
            if('2' == num) {
                num = '二';
            }
            if('3' == num) {
                num = '三';
            }
            if('4' == num) {
                num = '四';
            }
            if('5' == num) {
                num = '五';
            }
            if('6' == num) {
                num = '六';
            }
            if('7' == num) {
                num = '七';
            }
            if('8' == num) {
                num = '八';
            }
            if('9' == num) {
                num = '九';
            }
            if('0' == num) {
                num = '零';
            }
            return num;

        }

        /*===============================动态的获取输入价值的指并转换为中文表示===结束==============================*/



    });
</script>
</body>
</html>
