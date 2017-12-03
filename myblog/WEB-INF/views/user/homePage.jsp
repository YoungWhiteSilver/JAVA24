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
   <%@ include file="include/css.jsp" %>
    <link rel="stylesheet" href="/static/dist/css/style.css">
    <link rel="stylesheet" href="/static/uploader/webuploader.css" />
    <style>
        body {
            margin-top: 60px;
        }
        
    </style>
</head>
<body onunload="javascript:;"> 
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
						<li class=""><a
							href="/user/homePageServlet?nodeId=${node.id}">${node.nodename}</a></li>
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
                    				<!-- ${sessionScope.admin.headimgname} -->
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
                        <input type="text" class="form-control" placeholder="Search" id="keys" name="keys" value="${keys}">
                      </div>
                      <button class="btn btn-default"><i class="fa fa-search"></i></button>
                    </form>
                   
                  </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
         </nav>
              <!-- 导航栏结束 -->

              <!-- 文章列表开始 -->

        <div class="container">
            <div class="row">
                <div class="col-md-9">
                
                <c:forEach items="${page.pageItems}" var="article">
                
                   <div class="article-span">
                       <div class="media article">
                             <div class="media-body">
                                 <a href="/user/articleDetailServlet?articleId=${article.id}"><span class="media-heading"> ${article.title} </span></a> <span class="time">${article.createtime}</span>
								
								${article.simpContent}
								
                                 <div class="meta">
                                 <c:forEach items="${article.labelList}" var="label">
                                 	<a href="/user/homePageServlet?labelId=${label.id}"><span class="label label-primary">${label.labelname}</span></a> 
                                 </c:forEach>
                                
                             </div> 
                             </div>
                         
                             <div class="media-right">
								${article.pricture}
                             </div>
                                  
                          </div>
                        </div>
    			</c:forEach>
         <div class="text-center">
             <ul id="pagination" class="pagination pagination-lg"></ul>
         </div>
                </div>    
                <div class="col-md-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">浏览排行</h3>
                        </div>

                            <!-- List group -->
                   <ul class="list-group text-primary">
                   <c:forEach items="${hotArticle}" var="hotArt">
                    	<li class="list-group-item"><a href="/user/articleDetailServlet?articleId=${hotArt.id}">${hotArt.title}</a>  <label class="label label-warning">${hotArt.scannum}</label></li>
                   </c:forEach>
                   </ul>
                    </div>
                </div>
                
            </div>
        </div>
         
                <!-- 上传头像模态框 -->
<%--    <div class="modal fade" id="addModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">上传头像</h4>
        </div>
        <div class="modal-body">
              <div class="form-group">
              <form action="/admin/uploadHead" method="post" id="headForm" enctype="multipart/form-data">
                  <label class="col-sm-2 control-label">选择头像:</label>
                  <div class="col-sm-10">
                 <input type="hidden" id="adminId" name="adminId" value="${sessionScope.admin.id}"/>
                 <input type="file" name="file"/>
                  </div>
               </form>
                </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary pull-left" id="btn">上传</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div> --%>
<!-- 模态框结束 -->
        
    <!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>     
    <!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script src="/static/uploader/webuploader.min.js"></script>
<script src="/static/dist/js/time.js"></script>
<script src="/static/dist/js/moment.js"></script>
<script>
    $(function(){
    	
    	$(window).on('beforeunload', function(){
      	  return '确定要离开本页?';
      	});

        $("#pagination").twbsPagination({
        	
	         totalPages:"${page.pageTotal}",
	         visiblePages:3,
	         href:"/user/homePageServlet?p={{number}}&nodeId=${param.nodeId}&labelId=${param.labelId}&keys=" + encodeURIComponent("${param.keys}"),
	         first: "首页",
	         prev: "上一页",
	         next:"下一页",
	         last:"末页"
         
       }); 
        
        
     	
    });
</script>   
</body>
</html>