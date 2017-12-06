<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/12/5
  Time: 20:00
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
    <title>新增秒杀活动</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/js/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/js/datepicker/datepicker3.css">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/product">
                    <i class="fa fa-shopping-basket"></i> ProductStore
                </a>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-body">
                <form id="addCustomerForm" method="post" enctype="multipart/form-data">
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa fa-product-hunt fa-lg"></i></span>
                        <input type="text" class="form-control" name="productName" id="productName" placeholder="商品名称">
                    </div>
                    <br>
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa  fa-users fa-lg"></i></span>
                        <input type="text" class="form-control" name="productTitile" placeholder="商品副标题">
                    </div>
                    <br>
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa fa-smile-o fa-lg"></i></span>
                        <input type="text" class="form-control" name="productNumber" placeholder="商品数量">
                    </div>
                    <br>
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa fa-money fa-lg"></i></span>
                        <input type="text" class="form-control" name="productPrice" placeholder="秒杀价格">
                    </div>
                    <br>
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa fa-shopping-cart fa-lg"></i></span>
                        <input type="text" class="form-control" name="productMaketPrice" placeholder="市场价">
                    </div>

                    <br>
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa  fa-clock-o fa-lg"></i></span>
                        <input type="text" class="form-control" id="startTime" name="sTime" placeholder="开始时间">
                    </div>
                    <br>
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa  fa-clock-o fa-lg"></i></span>
                        <input type="text" class="form-control" id="endTime" name="eTime" placeholder="结束时间">
                    </div>

                    <br>
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa fa-picture-o fa-lg"></i></span>
                        <input type="file" class="form-control" name="file" placeholder="商品图片">
                    </div>
                    <br>
                    <div class="from-group">
                        <textarea name="productDesc" id="editor"  placeholder="商品描述"></textarea>
                    </div>
                    <br>
                </form>

                <button class="btn btn-primary" id="saveBtn">保存</button>
                <a href="/product" class="btn btn-default">&nbsp;返回</a>
            </div>
        </div>
    </div>

    <script src="/static/js/jquery-2.2.3.min.js"></script>
    <script src="/static/js/editer/scripts/module.js"></script>
    <script src="/static/js/editer/scripts/hotkeys.js"></script>
    <script src="/static/js/editer/scripts/uploader.js"></script>
    <script src="/static/js/editer/scripts/simditor.js"></script>
    <script src="/static/js/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/js/datepicker/bootstrap-datepicker.js"></script>
    <script src="/static/js/moment.js"></script>
    <script src="/static/js/layer/layer.js"></script>
    <script>
        $(function () {

            var editor = new Simditor({
                textarea: $('#editor')
            });

            var timepicker = $('#startTime').datetimepicker({
                format: "yyyy-mm-dd hh:ii",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true,
                startDate: moment().format("YYYY-MM-DD HH:mm")
            });

            timepicker.on("changeDate",function (ev) {
                var today =$("#startTime").val();
                $('#endTime').datetimepicker('setStartDate', today);

            });
            var picker = $('#endTime').datetimepicker({
                format: "yyyy-mm-dd hh:ii",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true,
            });

            $("#saveBtn").click(function () {

                if($("#productName").val()) {

                    $("#addCustomerForm").submit();

                } else {
                    layer.msg("请输入内容")
                }
            });


        });
    </script>
</body>
</html>