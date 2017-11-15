<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/14
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-新增待办事项</title>
    <%@ include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../include/left-meau.jsp">
        <jsp:param name="menu" value="task"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增待办事项</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool">
                            <i class="fa fa-plus"></i> 返回列表
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <input type="hidden" name="accountId" value="${sessionScope.curr_account.id}">
                        <div class="form-group">
                            <label>任务名称</label>
                            <input type="text" class="form-control" name="title">
                        </div>
                        <div class="form-group">
                            <label>完成日期</label>
                            <input type="text" class="form-control" id="datepicker" name="finishTime">
                        </div>
                        <div class="form-group">
                            <label>提醒时间</label>
                            <input type="text" class="form-control" id="datepicker2" name="remindTime">
                        </div>
                    </form>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button class="btn btn-primary" id="saveBtn">保存</button>
                </div>
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>

<script>
    $(function () {
        var picker = $('#datepicker').datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true,
            startDate:moment().format("yyyy-MM-dd")
        });
        picker.on("changeDate",function (e) {
            var today = moment().format("YYYY-MM-DD");
            $('#datepicker2').datetimepicker('setStartDate',today);
            $('#datepicker2').datetimepicker('setEndDate', e.format('yyyy-mm-dd'));
        });


        var timepicker = $('#datepicker2').datetimepicker({
            format: "yyyy-mm-dd hh:ii",
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        });

        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });

        $("#saveForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                title:{
                    required:true
                },
                finishTime:{
                    required:true
                }
            },
            messages:{
                title:{
                    required:"请输入任务内容"
                },
                finishTime:{
                    required:"请选择完成时间"
                }
            },
            submitHandler : function (form) {
                $.ajax({
                    url : "/task/new",
                    type : "post",
                    data : $(form).serialize(),
                    beforeSend : function () {
                        $("#saveBtn").text("正在提交").attr("disabled","disabled");
                    },
                    success : function (json) {

                        if(json.state == "success") {

                            window.location.href="/task";
                            layer.msg("保存成功");

                        } else {
                            layer.msg(json.message);
                        }
                    },
                    error : function () {
                        layer.msg("服务器跑了，正在追。。。。");
                    } ,
                    complete : function () {
                        $("#saveBtn").text("保存").removeAttr("disabled");
                    }
                })
            }
        });
    });
</script>
</body>
</html>
