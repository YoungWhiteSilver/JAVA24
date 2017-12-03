<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>写文章</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <%@ include file="include/css.jsp" %>
  <link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
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
      <div class="box box-primary">
          <div class="container">
            <form action="/admin/addArticleServlet" method="post" class="form-horizontal" id="editForm">
              <br>
               <div class="form-group">
               	  <input type="hidden" value="${artDetVo.article.id}" name="articleId"/>
                  <input type="text" class="form-control"  id="title" value="${artDetVo.article.title}" name="title">
               </div>
               
               <div class="form-group">
                  
                  <input type="text" class="form-control" 
                   value="<c:forEach items="${artDetVo.article.labelList}" var="label" varStatus="vs">${label.labelname}<c:if test="${not vs.last}">,</c:if></c:forEach>" 
                  id="label" name="label">
               </div>
               
               <div class="form-group"> 
                  <textarea name="editor" class="from-control" id="editor"  placeholder="正文从这里开始..." >${artDetVo.article.content}</textarea>
               </div>
              
               <div class="form-group">
                 <div class="col-sm-6" style="padding:0px">
                  <select name="nodeId" id="nodeId" class="form-control" style="margin-top:15px">
                      <option value="">请选择节点</option>
                      
                      <c:forEach items="${nodeList}" var="node">
                      
                      	<c:if test="${node.id == artDetVo.article.nodeid}">
                      		<option value="${node.id}" selected>${node.nodename}</option>
                      	</c:if>
                      		<option value="${node.id}">${node.nodename}</option>
                      </c:forEach>
                  </select>
                </div>
               </div>
               <br>
               <div class="form-group">
                  <button class="btn btn-primary" id="btnForm">修改文章</button>
               </div>
            </form>

          </div>
  
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
<%@ include file="include/uploadHead.jsp" %>
<!-- ./wrapper -->


<%@ include file="include/js.jsp" %>

</body>
<script>

  $(function(){
	  
	  	$("#btn").click(function(){
      	
      	$("#headForm").submit();
		
		});
		
	  	layer.alert("请先选择节点，如果节点无法满足，请去分类管理新增");
	  
		var editor = new Simditor({
			
			textarea: $('#editor'),
			upload : {
		        	
					url : "/admin/uploadPricture",
		        	fileKey : "file"
	       
			},
			toolbar: [
				'title',
            	'bold',
            	'ol',
            	'ul',
            	'color',
            	'fontScale',
            	'blockquote',
            	'code',
            	'image',
            	'indent',
            	'link',
            	'hr',
            	'alignment'
            	]
			
    	});
    
    	$("#btnForm").click(function() {
    		
    		if($("#editor").val()) {
    			
    			$("#editForm ").submit();
    			
    		} else {
    			
    			layer.msg("文章不能为空");
    			
    		}
    		
    	});
    	
    	$.validator.addMethod("labelValidate", function(value, element) {
  		  
  		  return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5]+(,[a-zA-Z0-9\u4e00-\u9fa5]+)*$/.test(value);
  		  
  	  	},"please notice your doc");
    	
    	$("#editForm").validate({
    		
    		errorClass : "text-danger",
    		errorElement : "span",
    		
    		rules : {
    			
    			title : {
    				required : true
    			},
    			label : {
    				required : true,
    				labelValidate : true
    			},
    			editor : {
  				  
  				  required : true
  				  
  			  	},
    			nodeId : {
    				required : true
    			}
    		},
    		
    		messages : {
    			title : {
  				  required : "请输入标题"
  			 	},
  				label : {
  				  required : "请输入标签",
  				  labelValidate : "标签不能有特殊字符，并且每个标签以英文 ,隔开"
  			 	},
 				editor : {
				  
				  required : "请输入内容"
				  
			  	},
  			 	nodeId : {
  				  required : "请选择分类"
  			 	}

    		},
    		
    		submitHandler : function(form) {
  			  $.ajax({
  				  url : "/admin/changeArticleServlet",
  				  type : "post",
  				  data : $(form).serialize(),
  				  beforeSend : function() {
  					  $("#btnForm").text("正在修改文章").attr("disabled", "disabled");
  				  },
  				  success : function(json) {
  					  if(json.state == "success") {
  						
  						  window.location.href = "/user/articleDetailServlet?articleId=" + json.data;
  					  
  					  } else {
  						  layer.msg(json.message);
  					  }
  				  },
  				  error : function() {
  					  layer.alert("系统异常，请稍后再试");
  				  },
  				  ccomplete : function() {
  					  
  					  $("#btnForm").text("修改文章").removeAttr("disabled", "disabled");
  					  
  				  }
  			  })
  		  }
    	});
    
  });

</script>
</html>