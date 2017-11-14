<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/14
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-待办事项</title>
    <%@ include file="../include/css.jsp"%>
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
                    <h3 class="box-title">待办事项</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增任务</a>
                        <c:choose>
                            <c:when test="${not (param.show == 'all')}">
                                <a href="/task?show=all" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示所有任务</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/task" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示未完成任务</a>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <div class="box-body">

                    <ul class="todo-list">
                        <c:forEach items="${taskList}" var="task">
                            <li class="${task.done==1 ? 'done' : ''}">
                                <input type="checkbox" class="task_checkbox" ${task.done==1 ? 'checked' : ''} value="${task.id}">
                                <span class="text">${task.title}</span>
                                    <%-- <c:choose>
                                         <c:when test="${not empty task.customer and not empty task.customer.id}">
                                             <a href="/customer/my/${task.customer.id}"><i class="fa fa-user-o"></i> ${task.customer.custName}</a>
                                         </c:when>
                                         <c:when test="${not empty task.saleChance and not empty task.saleChance.id}">
                                             <a href="/sales/my/${task.saleChance.id}"><i class="fa fa-money"></i> ${task.saleChance.name}</a>
                                         </c:when>
                                     </c:choose>--%>
                                <small class="label ${task.overTime ? 'label-danger' : 'label-success'}"><i class="fa fa-clock-o"></i> ${task.finishTime}</small>
                                <div class="tools">
                                    <i class="fa fa-edit"></i>
                                    <i class="fa fa-trash-o del_task" rel="${task.id}"></i>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- /.box-body -->
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
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>


<script>
    $(function () {

        //删除
        $(".del_task").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除吗",function () {
                window.location.href = "/task/"+id+"/del";
            });
        });

        //修改状态
        $(".task_checkbox").click(function () {
            var id = $(this).val();
            var checked = $(this)[0].checked;
            if(checked) {
                window.location.href = "/task/"+id+"/state/done";
            } else {
                window.location.href = "/task/"+id+"/state/undone"
            }
        });
    });
</script>

</body>
</html>
