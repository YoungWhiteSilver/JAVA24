<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <header class="main-header">
        <!-- Logo -->
        <a href="/admin/managerArticleServlet" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini">博客</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg">我的博客</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
           
          </a>
    
          <div class="navbar-custom-menu">
              <ul class="nav navbar-nav">
                 <c:choose>
                    	<c:when test="${not empty sessionScope.admin.username}">
                    		<li class="dropdown user user-menu">
                    			<!-- 模态框开关 -->
                    			<a href="#" class="btn pull-right"  data-toggle="modal" data-target="#addHeadModal" >
                    		
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
                 
                  <!-- Notifications: style can be found in dropdown.less -->
                  <li class="dropdown notifications-menu">
                    <a href="/admin/notifyServlet" class="dropdown-toggle">
                      <i class="fa fa-bell-o"></i>
                      <span class="label label-warning" id="unRead"></span>
                    </a>
                
                  </li>
                 
                  <li class="dropdown user user-menu">
                      <a href="/admin/loginoutServlet" class="dropdown-toggle" >
                        <span class="hidden-xs"><i class="fa fa-sign-in"></i> </span>
                      </a>
                      
                    </li>
                </ul>
              </div>
            </nav>
      </header>

  <!-- =============================================== -->

  <!-- Left side column. contains the sidebar -->
  <aside class="main-sidebar">
      <!-- sidebar: style can be found in sidebar.less -->
      <section class="sidebar">
       
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
         <li class="treeview">
            <a href="/user/homePageServlet">
              <i class="fa fa-home"></i> <span>博客首页</span>
            </a>
          </li>
         
          <li class="treeview">
            <a href="/admin/managerArticleServlet">
              <i class="fa fa-file-text "></i> <span>文章管理</span>
            </a>
            
          </li>
          <li class="treeview">
            <a href="/admin/managerNodeServlet">
              <i class="fa fa-files-o"></i> <span>分类管理</span>
            </a>
           
          </li>
          <li class="treeview">
            <a href="/admin/managerReplyServlet">
              <i class="fa fa-comments"></i> <span>评论管理</span>
            </a>
          </li>
          
        </ul>
      </section>
      <!-- /.sidebar -->
    </aside>