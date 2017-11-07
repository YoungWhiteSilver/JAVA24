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
    <%@include file="include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css">

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

<%@include file="include/header.jsp"%>
    <!-- =============================================== -->
<%@include file="include/left-meau.jsp"%>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-2">
                    <div class="box">
                        <div class="box-body">
                            <button class="btn btn-default">添加部门</button>
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
                                <button type="button" class="btn btn-box-tool"  title="Collapse">
                                    <i class="fa fa-plus"></i> 添加员工</button>
                            </div>
                        </div>
                        <div class="box-body">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>部门</th>
                                    <th>手机</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${pageInfo.list}" var="dept">
                                    <c:forEach items="${dept.accountList}" var="account">
                                        <tr>
                                            <td>${account.userName}</td>
                                            <td>${dept.deptName}</td>
                                            <td>${account.mobile}</td>
                                            <td>${account.createTime}</td>
                                            <td>
                                                <a href="">禁用</a>
                                                <a href="">删除</a>
                                                <a href="">编辑</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
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

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>

<script>
    $(function(){

        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey : "id",
                    pIdKey : "pId",
                    rootPId : 0
                }
            },

            callback:{
                onClick:function(event,treeId,treeNode,clickFlag){
                    /*alert(treeNode.id + treeNode.name + treeNode.pId);*/

                }
            }
        };

        var zNodes = [];
        <c:forEach items="${pageInfo.list}" var="dept">

            zNodes.push({
                id: ${dept.id},
                pId : ${dept.pId == null ? 0 : dept.pId},
                name : '${dept.deptName}'
            });

        </c:forEach>

          /*  zNodes.push({
                id: deptId,
                pId : deptId,
                name : deptName
                });*/


       /* var zNodes =[
            { id:1, pId:0, name:"凯盛软件", open:true},
            { id:11, pId:1, name:"开发部"},
            { id:111111, pId:11, name:"华北开发部"},
            { id:111, pId:1, name:"销售部"},
            { id:112, pId:1, name:"经理办公室"}
        ];*/
        $.fn.zTree.init($("#ztree"), setting, zNodes);
    });

</script>
</body>
</html>

</body>
</html>
