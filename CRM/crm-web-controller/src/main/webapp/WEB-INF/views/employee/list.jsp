<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/7
  Time: 18:32
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
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">
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
    <jsp:param name="menu" value="home" />
</jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-2">
                    <div class="box">
                        <div class="box-body">
                            <button class="btn btn-default" id="addDept">添加部门</button>
                            <input type="hidden" id="deptId">
                            <ul id="ztree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-10">
                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">员工管理</h3>

                            
                            <div class="box-tools pull-right">
                                <a href="javascript:;" id="delDept"></a>
                               <%-- <button  type="button" class="btn btn-box-tool"  id="delDept">
                                    <i class="fa fa-trash-o"></i>
                                    删除部门
                                </button>--%>
                                <button  type="button" class="btn btn-box-tool" id="addEmployBtn">
                                    <i class="fa fa-plus"></i>
                                    添加员工
                                </button>
                            </div>
                        </div>
                        <div class="box-body">
                            <table class="table  table-striped" id="dataTable">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>姓名</th>
                                    <th>部门</th>
                                    <th>手机</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>

                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->


<%--添加账号模态框--%>
<!-- Modal -->
<div class="modal fade" id="addEmployeeModel" >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加账号</h4>
            </div>
            <div class="modal-body">
                <form id="addEmployeeForm">
                    <div class="form-group">
                        <label>姓名</label>
                        <input type="text" class="form-control" name="userName">
                    </div>
                    <div class="form-group">
                        <label>手机号码</label>
                        <input type="text" class="form-control" name="mobile">
                    </div>
                    <div class="form-group">
                        <label>密码(默认000000)</label>
                        <input type="password" class="form-control" name="password" value="000000">
                    </div>
                    <div class="form-group">
                        <label>所属部门</label>
                        <div id="checkboxList"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="addEmployeeFormBtn">保存</button>
            </div>
        </div>
    </div>
</div>
<%--添加账号模态框结束--%>




<!-- jQuery 2.2.3 -->
<%@include file="../include/js.jsp"%>>

<script>
    $(function(){

        /*====================================右侧列表======================s================*/
        var dataTable = $("#dataTable").DataTable({

            "processing" : true,
            "serverSide" : true,
            "ajax" : {
                "url" : "/employee/table.json",
                "data" : function (data) {
                    data.deptId = $("#deptId").val();
                }
            },
            "lengthChange" : false,
            "pageLength" : 10,
            "columns" : [
                {"data" : "id"},
                {"data" : "userName"},
                {"data" : function (row) {
                    var deptArray = row.deptList;
                    var str = '';
                    for(var i = 0; i < deptArray.length; i ++) {
                      str += deptArray[i].deptName + "&nbsp";
                    }
                    return str;
                }},
                {"data" : "mobile"},
                {"data" : function (row) {
                    return "<a href='javascript:;' rel='+ row.id +' class='delEmployee'>删除</a>";
                }}
            ],
            "columnDefs" : [
                {
                    "targets" : [2,3,4],
                    "orderable" : false
                },
                {
                    "targets" : [0],
                    "visible" : false
                }
            ],

            "language" :{
                "search":"账号:",
                "info": "显示从 _START_ 到 _END_ 条数据，共 _TOTAL_ 条",
                "infoEmpty":"没有任何数据",
                "infoFiltered":   "",
                "emptyTable":"暂无数据",
                "processing":"加载中...",
                "zeroRecords": "没有找到数据",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });
        /*====================================dataTable结束======================================*/

        /*====================================添加员工======================================*/

        $("#addEmployBtn").click(function () {
            $("#checkboxList").html("");

            $.ajax({
                url : "/employee/dept.json",
                type : "get",
                success : function (json) {
                    for(var i = 0; i < json.length; i ++) {
                        var dept = json[i];
                        if(dept.id != 1) {
                            var html = '<label class="checkbox-inline"><input type="checkbox" name="deptId" value="'+dept.id+'">'+dept.deptName+'</label>';
                            $("#checkboxList").append(html);
                        }
                    }
                    $("#addEmployeeModel").modal({
                        show:true,
                        backdrop:'static'
                    });
                },
                error : function () {
                    layer.msg("服务器跑了，正在追。。。");
                },

            });
        });

        $("#addEmployeeFormBtn").click(function () {
            $("#addEmployeeForm").submit();
        });

        $("#addEmployeeForm").validate({

            errorClass : "text-danger",
            errorElement : "span",

            rules : {
                userName : {
                    required : true
                },
                password : {
                    required : true
                },
                mobile : {
                    required : true
                },
                deptId : {
                    required : true
                }
            },

            messages : {
                userName : {
                    required : "请输入账号"
                },
                password : {
                    required : "请输入密码"
                },
                mobile : {
                    required : "请输入手机号"
                },
                deptId : {
                    required : "请选择部门"
                }
            },

            submitHandler : function (form) {

                $.ajax({

                    url : "/employee/new",
                    type : "post",
                    data :  $(form).serialize(),
                    beforeSend : function() {
                        $('#addEmployeeFormBtn').text("正在验证").attr("disabled", "disabled");
                    },

                    success : function(json) {
                        if(json.state == "success") {

                            $("#addEmployeeModel").modal('hide');
                            layer.msg("保存成功");
                            dataTable.ajax.reload();

                        } else {
                            layer.msg(json.message);
                        }
                    },
                    error : function () {
                        layer.msg("服务器跑了，正在追。。。。");
                    },
                    complete : function () {
                        $("#addEmployeeFormBtn").text("保存").removeAttr("disabled");
                    }
                });
            }

        })


        /*====================================左侧树状图======================================*/

        $("#addDept").click(function () {
            layer.prompt({title:"请输入部门名称"},function (text, index) {
                layer.close(index);
                $.ajax({
                    url : "/employee/dept/new",
                    type : "post",
                    data : {
                        "dataName" : text
                    },
                    beforeSend : function () {

                        $("#addDept").text("正在添加").attr("disabled", "disabled");

                    },
                    success : function (json) {

                        if(json.state ==  "success") {
                            layer.msg("添加部门成功");

                            /*刷新树*/
                            var treeObj = $.fn.zTree.getZTreeObj("ztree");
                            treeObj.reAsyncChildNodes(null, "refresh");

                        } else {
                            layer.msg(json.message)
                        }

                    },
                    error : function () {
                        
                    },
                    complete : function () {
                        $("#addDept").text("添加部门").removeAttr("disabled");
                    }
                });
            })


        });

        var setting = {
            data: {
                simpleData: {
                    enable: true
                },
                key: {
                    name: "deptName"
                }
            },
            async: {
                enable: true,
                url: "/employee/dept.json",
                type:"get",
                dataFilter: ajaxDataFilter
            },

            callback:{
                onClick:function(event,treeId,treeNode,clickFlag){
                    /*alert(treeNode.id + treeNode.name + treeNode.pId);*/
                    $("#delDept").html("");

                    if(treeNode.id != 1) {

                        var html = "<button  type=\"button\" class=\"btn btn-box-tool\"  id=\"delDeptBtn\">" +
                            "<i class=\"fa fa-trash-o\"></i>" +
                            " 删除部门" +
                            "</button>";

                        $("#delDept").append(html);

                    }

                    $("#deptId").val(treeNode.id);
                    dataTable.ajax.reload();
                }
            }
        };

        function ajaxDataFilter(treeId, parentNode, responseData) {
            if(responseData) {
                for(var i = 0; i < responseData.length; i ++) {
                    if(responseData[i].id == 1) {
                        responseData[i].open= true;
                        break;
                    }
                }
            }
            return responseData;
        }

        $.fn.zTree.init($("#ztree"), setting);
        /*===============================左侧树状图=========结束================================*/


        <%--var zNodes = [];--%>
        <%--<c:forEach items="${pageInfo.list}" var="dept">--%>

        <%--zNodes.push({--%>
        <%--id: ${dept.id},--%>
        <%--pId : ${dept.pId == null ? 0 : dept.pId},--%>
        <%--name : '${dept.deptName}'--%>
        <%--});--%>

        <%--</c:forEach>--%>

        /* var zNodes =[
             { id:1, pId:0, name:"凯盛软件", open:true},
             { id:11, pId:1, name:"开发部"},
             { id:111111, pId:11, name:"华北开发部"},
             { id:111, pId:1, name:"销售部"},
             { id:112, pId:1, name:"经理办公室"}
         ];*/
    });

</script>
</body>
</html>

</body>
</html>
