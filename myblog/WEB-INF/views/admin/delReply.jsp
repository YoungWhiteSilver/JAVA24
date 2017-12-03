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
          <form action="/admin/ReplyDetailServlet" class="form-inline pull-left" >
          	<input type="hidden" name="articleId" value="${artDetVo.article.id}"/>
            <input type="text" class="form-control" name="keys" id="keys" value="${keys}"/>
            <button class="btn btn-primary"><i class="fa fa-search"></i></button>
          </form>
         
         
        </div>
		
        <div class="box-body">
          <div class="page-header">
				<h1>${artDetVo.article.title}</h1>
				<h4>创建时间 : ${artDetVo.article.createtime}</h4>
			</div>	
		  <br>
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>评论内容</th>
                <th>评论IP</th>
                <th>评论时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
             
              <c:forEach items="${page.pageItems}" var="reply">
              	 <tr>
                  <td>${reply.content}</td>
                  <td>${reply.userIp}</td>
                  <td>${reply.createtime}</td>
                  <td>
                    <a href="javascript:;" class="del" rel="${reply.id}" artId="${artDetVo.article.id}">删除</a> 
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
      <b>Version</b> 2.3.8
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>


</div>

<%@ include file="include/js.jsp" %>

<!-- DataTables -->
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
      
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:3,
         href:"/admin/ReplyDetailServlet?p={{number}}&articleId=${artDetVo.article.id}&keys=" + encodeURIComponent("${param.keys}"),
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  
      
		$(".del").click(function() {
			
			var replyId = $(this).attr("rel");
			var articleId = $(this).attr("artId");
        	layer.confirm("确定要删除该条评论么？",function(){

        		$.ajax({
					
        			url : "/admin/delReply",
        			type : "post",
        			data : {
        				"replyId" : replyId
        			},
        			
        			success : function(json) {
        				
        				if(json.state == "success") {
        					
        					layer.alert("删除成功",function(){
    	       					history.go(0);
    	       				});
        					
        				} else {
        					
        					layer.msg(json.message);
        				}
        			},
        			
        			error : function() {
        				
        				layer.msg("系统出错了");
        			
        			}
        			
        		});
        	})
		});
        
        
    });
    </script>
</body>
</html>
