 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
      <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/dist/css/style.css">
    <style>
    
        body {
            margin-top: 60px;
        }
        
    </style>
</head>
<body>
         <nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
                <div class="container">
                  <!-- Brand and toggle get grouped for better mobile display -->
                  <div class="navbar-header">
                    <a class="navbar-brand" href="/user/homePageServlet"><i class="fa fa-drupal"></i></a>
                  </div>
              
                  <!-- Collect the nav links, forms, and other content for toggling -->
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                      <li><a href="/user/homePageServlet">全部</a></li>
                    
					<c:forEach items="${nodeList}" var="node">
						<li class="">
							<a href="/user/homePageServlet?nodeId=${node.id}">${node.nodename}</a>
						</li>
					</c:forEach>
				</ul>
                    
                    <!-- 头像 -->
                    <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                    	<c:when test="${not empty sessionScope.admin.username}">
                    		<li class="dropdown user user-menu">
                    			<a href="/admin/managerArticleServlet" class="btn pull-right">
                    			<c:choose>
                    				<c:when test="${not empty sessionScope.admin.headimgname}">
                    				<img src="${sessionScope.admin.headimgname}" class="user-image">
                    				</c:when>
                    				<c:otherwise>
                    				<img src="/static/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                    				</c:otherwise>
                    			</c:choose>
                      			<span class="hidden-xs">${sessionScope.admin.username}</span>
                    			</a>
                    			
                    		</li>
                    	</c:when>
                    	<c:otherwise>
                    		<li><a href="/user/loginServlet">登录</a></li>
                    	</c:otherwise>
                    </c:choose>
                    </ul>
                    <!-- 头像 -->
                    
                    <form class="navbar-form navbar-right" action="/user/homePageServlet">
                      <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" id="keys" name="keys">
                      </div>
                      <button class="btn btn-default"><i class="fa fa-search"></i></button>
                    </form>
                   
                  </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
         </nav>
              <!-- 导航栏结束 -->

              <!-- 文章列表开始 -->

              <div class="container">
                    <div class="box">
                            <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
                                <li><a href="/user/homePageServlet">首页</a></li>
                                <li class="active">${artDetVo.article.nodeName}</li>
                            </ul>
                            
                            <div class="topic-head">
                                <h3 class="title">${artDetVo.article.title}</h3>
                             
                            </div>
                           
                            <div class="topic-body">
                       				${artDetVo.article.content}
                                </div>
                            <div class="topic-toolbar">
                                   
                                <ul class="list-inline text-muted">
                                    <li><i class="fa fa-eye"></i>${artDetVo.article.scannum}</li>
                                    <li><i class="fa fa-commenting"></i> ${artDetVo.article.replynum}</li>
                                </ul>
                            </div>
                        </div>
                       
                        <!--box end-->
                
                        <div class="box" style="margin-top:20px;">
                            <div class="talk-item muted" style="font-size: 12px">
                                	${artDetVo.article.replynum}个回复 
                                	<c:if test="${not empty artDetVo.article.lastreplytime}">
                                		|直到<span id="lastreplytime">${artDetVo.article.lastreplytime}</span>
                                	</c:if>
                                	 
                            </div>
                            <c:forEach items="${artDetVo.replyList}" var="reply" varStatus="vs">
                            	<c:if test="${reply.pid == 0}">
                            		<div class="talk-item">
		                                <table class="talk-table">
		                                    <tr>
		                                        <td width="auto">
		                                            <a href="#" style="font-size: 12px">${reply.userIp}</a> <span style="font-size: 12px" class="reply">${reply.createtime}</span>
		                                            <br>
		                                            
		                                           <p style="font-size: 14px">${reply.content}</p>
		                                        </td>
		                                        <td width="70" align="right" style="font-size: 12px">
		                                            <a href="javascript:;" class="replylink" rel="${reply.userIp}" replyId="${reply.id}" title="回复"><i class="fa fa-reply"></i></a>
		                                       		<span class="badge">${vs.count}</span>
		                                        </td>
		                                        
		                                    </tr>
		                                </table>
		                                
		                                <c:forEach items="${artDetVo.replyList}" var="reReply" varStatus="vs">
		                                 	<c:if test="${reply.id == reReply.pid}">
		                                 		
		                                 		<div class="talk-item">
					                                <table class="talk-table">
					                                    <tr>
					                                        <td width="auto">
					                                            <a href="#" style="font-size: 12px">${reReply.userIp}</a> <span style="font-size: 12px" class="reply">${reReply.createtime}</span>
					                                            <br>
					                                           <p style="font-size: 14px">${reReply.content}</p>
					                                        </td>
					                                        
					                                    </tr>
					                                </table>
		                                 		</div>	
		                                 	</c:if>	
		                                </c:forEach>
		                                
                            		</div>
                            	</c:if>
                		</c:forEach>
                	</div>
                
                <!-- 评论表单 -->
                        <div class="box" style="margin:20px 0px;">
                        	<a name="reply"></a>
                            <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
                            <form action="/user/addArticleDetailServlet" name="editorForm" method="post" id="editorForm" style="padding: 15px;margin-bottom:0px;">
                            	<input type="hidden" name="userIp" value="${sessionScope.admin.username}"/>
                                <input type="hidden" name="articleId" value="${artDetVo.article.id}"/>
                                <input type="hidden" name="replyId" id="replyId" value=""/>
                                <textarea name="editor" id="editor"></textarea>
                            </form>
                            <div class="talk-item muted" style="text-align: right;font-size: 12px">
                                <span class="pull-left">请尽量让自己的回复能够对别人有帮助回复</span>
                                <button class="btn btn-primary" id="btn">发布</button>
                            </div>
                        </div>
			</div>
              
    <!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.js"></script>     
<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/dist/js/moment.js"></script>
<script src="/static/dist/js/jquery.validate.js"></script>
<script>
    $(function(){
        
    	var editor = new Simditor({
           textarea: $('#editor'),
           toolbar: [
           	'bold',
           	'color',
           	'code',
           	]
        });
    	
    	moment.locale("zh-cn");
    	$("#lastreplytime").text(moment($("#lastreplytime").text()).format("YYYY年MM月DD日 HH:mm:ss"));
    	
    	$(".reply").text(function(){
        	var time = $(this).text();
        	return moment(time).fromNow();
        });
        
 		$("#btn").click(function() {
 			
 			if($("#editor").val()) {
 				
 				$("#editorForm").submit();
 				
 			} else {
 				
 				layer.msg("请输入评论内容");
 				
 			}
 			
 		});
 		
 		$("#editorForm").validate({
 			
 			submitHandler : function(form) {
 	  			  $.ajax({
 	  				  url : "/user/addArticleDetailServlet",
 	  				  type : "post",
 	  				  data : $(form).serialize(),
 	  				  beforeSend : function() {
 	  					  $("#btn").text("正在发布评论").attr("disabled", "disabled");
 	  				  },
 	  				  success : function(json) {
 	  					  if(json.state == "success") {
 	  						
 	  						window.location.href = json.data;
 	  					  
 	  					  } else {
 	  						  
 	  						  layer.msg(json.message);
 	  					  
 	  					  }
 	  				  },
 	  				  
 	  				  error : function() {
 	  					  layer.alert("系统异常，请稍后再试");
 	  				  },
 	  				  
 	  				  complete : function() {
 	  					  
 	  					  $("#btn").text("发布").removeAttr("disabled", "disabled");
 	  				  }
 	  			  })
 	  		  }
 			
 		});
       
 		$(".replylink").click(function() {
			 			
 			var userIp = $(this).attr("rel");
 			var replyId = $(this).attr("replyId");
 			$("#replyId").val(replyId);
 			window.location.href = "#reply";
 			
 			var html = "回复" + userIp + "：";
        	editor.setValue(html);
        	editor.focus();
 			
 		});
 		
    });
</script>   
</body>
</html>