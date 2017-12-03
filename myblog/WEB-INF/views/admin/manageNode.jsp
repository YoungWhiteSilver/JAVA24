<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>节点列表</title>
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
          <form action="/admin/managerNodeServlet" class="form-inline pull-left" >
            <input type="text" class="form-control" name="keys" id="keys" value="${keys}"/>
            <button class="btn btn-primary"><i class="fa fa-search"></i></button>
          </form>
         <!--  <h5 class="pull-left">文章列表</h5> -->
          <a href="#" class="btn btn-success pull-right"  data-toggle="modal" data-target="#addNodeModal">新增节点</a>
        </div>
        <div class="box-body">
        	<div class="page-header">
				<h1>分类管理</h1>
			</div>
        
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>节点名称</th>
                <th>创建时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${page.pageItems}" var="node"> 
              	<c:choose>
              		<c:when test="${node.id <= 5}">
	              		<tr>
			                <td><a href="/admin/managerArticleServlet?nodeId=${node.id}">${node.nodename}</a></td>
			                <td class="time">${node.createtime}</td>
			                <td >
			                	<p class="text-success">系统默认，不允许修改和删除！</p>
			                </td>
	              		</tr>
              		</c:when>
              		<c:otherwise>
	              		<tr>
			                <td><a href="/admin/managerArticleServlet?nodeId=${node.id}">${node.nodename}</a></td>
			                <td class="time">${node.createtime}</td>
			                <td>
			                  <a class="del" href="javascript:;" rel="${node.id}">删除</a> 
			                  <a href="javascript:;" class="update" rel="${node.nodename}"  nodeid="${node.id}">修改</a> 
			                </td>
	              		</tr>
              		</c:otherwise>
              	</c:choose>
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
</div>
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.8
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>

<!-- 新增节点  addForm -->


  <div class="modal fade" id="addNodeModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">新增节点</h4>
        </div>
        <div class="modal-body">
          <form action="/admin/nodeAdd" method="post" class="form-horizontal" id="addForm">
              <div class="form-group">
                  <label class="col-sm-2 control-label">节点名称:</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="addNodeName" name="addNodeName" placeholder="请输入节点名称">
                  </div>
                </div>

          </form>
        </div>
        <div class="modal-footer">
          <button id="addNodeBtn" class="btn btn-primary pull-left">保存</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

<!-- 修改节点  updateForm-->


<div class="modal fade" id="updateModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">更新节点</h4>
        </div>
        <div class="modal-body">
          <form action="/admin/nodeUpdate" method="post" class="form-horizontal" id="updateForm">
              <div class="form-group">
                  <label class="col-sm-2 control-label">节点名称:</label>
                  <div class="col-sm-10">
                  <input type="hidden" id="nodeId" name="nodeId" value="${node.id}"/>
                    <input type="text" class="form-control" id="updateNodeName" name="updateNodeName" placeholder="请输入节点名称">
                  </div>
                </div>

          </form>
        </div>
        <div class="modal-footer">
          <button id="updateBtn" class="btn btn-primary pull-left">保存</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



</div>
<!-- ./wrapper -->
<%@ include file="include/js.jsp" %>
<!-- DataTables -->
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
    	
		$("#btn").click(function(){
        	
        	$("#headForm").submit();
		
		});
      
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:3,
         href:"/admin/managerNodeServlet?p={{number}}&keys=" + encodeURIComponent("${param.keys}"),
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       	});  
      

       $(".update").click(function(){
    	 $("#nodeId").val($(this).attr("nodeid"));
         $("#updateNodeName").val($(this).attr("rel"));
          $('#updateModal').modal({
            keyboard: false
          });
       });
       
       
       $(".del").click(function() {
    	 	
    	    var id = $(this).attr("rel");
     	  	layer.confirm("这会删除该节点下的所有内容，确定要删除么？",function(){
	       		$.get("/admin/delNodeServlet",{"nodeId":id}).done(function(json){
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
       
       
       $("#addNodeBtn").click(function() {
    	   $("#addForm").submit();
       });
	
       $("#addForm").validate({
    	   
    	   errorClass : "text-danger",
    	   errorElement : "span",
    	   
    	   rules : {
    		   addNodeName : {
    			  required : true,
    			  remote : "/admin/nodeAdd"
    		  }
    	   },
    	   
    	   messages : {
    		   addNodeName : {
     			  required : "请输入节点",
     			  remote : "该节点已经存在"
     		  } 
    	   },
    	   
       });
       
       $("#updateBtn").click(function() {
    	   $("#updateForm").submit();
       });
	
       $("#updateForm").validate({
    	   
    	   errorClass : "text-danger",
    	   errorElement : "span",
    	   
    	   rules : {
    		  updateNodeName : {
    			  required : true,
    			  remote : "/admin/nodeUpdate"
    		  }
    	   },
    	   
    	   messages : {
    		   updateNodeName : {
     			  required : "请输入节点",
     			  remote : "该节点已经存在"
     		  } 
    	   }
    	   
       });
      
    });
    </script>
</body>
</html>
    