<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>文章列表</title>
<!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.6 -->
<%@ include file="include/css.jsp" %>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

<%@ include file="include/body.jsp" %>
  
<!-- =============================================== -->

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
<!-- Main content -->
    <section class="content">
<!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
         
          <form action="/admin/managerReplyServlet" class="form-inline pull-left" >
            <input type="text" class="form-control" name="keys" id="keys" value="${keys}"/>
            <button class="btn btn-primary"><i class="fa fa-search"></i></button>
          </form>
          
        </div>

        <div class="box-body">
        	<div class="page-header">
				<h1>评论管理</h1>
			</div>
          
          <table class="table" id="cust_table">
            <thead>
              <tr>
                <th>文章标题</th>
                <th>模块</th>
                <th>标签</th>
                <th>发表时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              
              <c:forEach items="${page.pageItems}" var="article">
               <tr>
                  <td><a href="/admin/ReplyDetailServlet?articleId=${article.id}">${article.title}</a></td>
                  <td><a href="/admin/managerReplyServlet?nodeId=${article.nodeid}">${article.nodeName}</a></td>
                  <td>
                  	<c:forEach items="${article.labelList}" var="label" varStatus="vs">
                  		${label.labelname}
                  	<c:if test="${not vs.last}">
                  		/
                  	</c:if>
                  	</c:forEach>
                  </td>
                  <td class="time">${article.createtime}</td>
                  <td>
                    <a href="/admin/ReplyDetailServlet?articleId=${article.id}">管理评论</a> 
                  </td>
                </tr>
              
              </c:forEach>
               
            </tbody>
          </table>
          <br> 
          <ul id="pagination" class="pagination pull-right"></ul>
        </div>
<!-- /.box-body -->
        
      </div>
<!-- /.box -->

    </section>
<!-- /.content -->
  </div>
<!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.2.0
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>
</div>
 
<%@ include file="include/uploadHead.jsp" %>

<!-- ./wrapper -->
<%@ include file="include/js.jsp" %>
<!-- DataTables -->
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- AdminLTE App -->
<!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
      
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:3,
         href:"/admin/managerReplyServlet?p={{number}}&nodeId=${param.nodeId}&labelId=${param.labelId}&keys=" + encodeURIComponent("${param.keys}"),
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       }); 
        
		$("#btn").click(function(){
        	
        	$("#headForm").submit();
		
		});
		
		$(".del").click(function() {
			
			var id = $(this).attr("rel");
			
        	layer.confirm("这会删除文章下的所有内容，确定要删除么？",function(){
	       		$.get("/admin/delArticleServlet",{"articleId":id}).done(function(json){
	       			if(json.state == "success") {
	       				layer.alert("删除成功",function(){
	       					history.go(0);
	       				})
	       			} else {
	       				
	       				layer.msg(json.message);
	       				
	       			}
	       			
	       		}).error(function(){
	       			alert("系统异常")
	       		});
        	})
			
		});
      
    });
    </script>
</body>
</html>