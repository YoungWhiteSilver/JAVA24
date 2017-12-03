<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>消息中心</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
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
       	 
				
			
            <div class="box-header">
				<h1>消息通知</h1>
				<button id="markBtn" style="margin-left: 8px;" disabled class="btn btn-success">标记为已读</button>
			</div>
			
			
          
        </div>

        <div class="box-body">
         	
          
          <table class="table">
            <thead>
                <tr>
                    <th width="40"><input type="checkbox" id="ckFather"></th>
                    <th width="300">发布日期</th>
                    <th>内容</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
               <c:forEach items="${page.pageItems}" var="notify">
					
			        <c:choose>
			        	<c:when test="${notify.state == 0}">
			        		<tr >
					            <td><input value="${notify.id}" type="checkbox" class="ckSon"></td>
					            <td class="time">${notify.createTime}</td>
					            <td><a href="/admin/readNotifyServlet?id=${notify.id}&aid=${notify.aid}">${notify.content}</a></td>
					            <td><a href="javascript:;" class="del" rel="${notify.id}">删除</a></td>
			        		</tr>
			        	</c:when>
			        	<c:otherwise>
			        		<tr class="active">
			        			<td></td>
			        			<td class="time">${notify.createTime}</td>
					            <td>${notify.content}</td>
					            <td><a href="javascript:;" class="del" rel="${notify.id}">删除</a></td>
			        		</tr>
			        	</c:otherwise>
			        
			        </c:choose>
               </c:forEach>
                          
							<!--  <tr>
                                <td><input value="1" type="checkbox" class="ckSon"></td>
                                <td>2016-10-22 10:12:22</td>
                                <td>您发布的主题<a>哈哈哈哈</a>有了新的回复</td>
                            </tr> <tr>
                                <td><input value="1" type="checkbox" class="ckSon"></td>
                                <td>2016-10-22 10:12:22</td>
                                <td>您发布的主题<a>哈哈哈哈</a>有了新的回复</td>
                            </tr>
							 <tr>
                                <td><input value="1" type="checkbox" class="ckSon"></td>
                                <td>2016-10-22 10:12:22</td>
                                <td>您发布的主题<a>哈哈哈哈</a>有了新的回复</td>
                            </tr> -->
                      
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

<!-- ./wrapper -->
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
         href:"/admin/notifyServlet?p={{number}}",
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       }); 
        
        $("#ckFather").click(function(){
        	
        	var sons = $(".ckSon");
        	
        	for(var i = 0; i < sons.length; i ++) {
        		
        		sons[i].checked = $(this)[0].checked;
        		
        	}
        	
        	if($(this).is(":checked") == true) {
        		
        		$("#markBtn").removeAttr("disabled");
        		
        	} else {
        		
        		$("#markBtn").attr("disabled", "disabled");
        	}
        	
        });
        
        $(".ckSon").click(function() {
        	
        	var sons = $(".ckSon");
        	var num = 0;
        	
        	for(var i = 0; i < sons.length; i ++) {
        		
        		if(sons[i].checked == true) {
        			num ++;
        		}
        		
        	}
        	
        	if(num == sons.length) {
        		$("#ckFather")[0].checked = true;
        	} else {
        		$("#ckFather")[0].checked = false;
        	}
        	
        	if(num > 0) {
        		
        		$("#markBtn").removeAttr("disabled");
        		
        	} else {
        		
        		$("#markBtn").attr("disabled", "disabled");
        		
        	}
        	
        });
        
        $("#markBtn").click(function() {
        	
        	var ids = [];
        	var sons = $(".ckSon");
        	
        	for(var i = 0; i < sons.length; i ++) {
        		
        		if(sons[i].checked == true) {
        			ids.push(sons[i].value);
        		}
        		
        	}
        	
        	$.ajax({
        		
        		url : "/admin/readNotifyServlet",
        		type : "post",
        		data : {
        			"ids" : ids.join(",")
        		},
        		beforeSend : function() {
        			
        			$("#markBtn").attr("disabled", "disabled");
        			
        		},
        		success : function(json) {
        			
        			if(json.state == "success") {
        				
        				history.go(0);
        				
        			} else {
        				
        				layer.msg(json.message);
        				
        			}
        			
        		},
        		error : function() {
        			
        			
        		},
        		complete : function() {
        			
        			
        		}
        	})
        	
        });
        
        $(".del").click(function() {
        	
			var notifyId = $(this).attr("rel");
			
			layer.confirm("确定要删除这条消息吗？",function(){
	       		$.get("/admin/delNotifyServlet",{"notifyId": notifyId}).done(function(json){
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
    