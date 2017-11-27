<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/7
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- 左侧菜单栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- 搜索表单，不需要删除即可 -->
        <!--<form action="#" method="get" class="sidebar-form">
          <div class="input-group">
            <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                  <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                  </button>
                </span>
          </div>
        </form>-->
        <!-- /.search form -->
        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="header">系统功能</li>

            <li class="treeview ${fn:startsWith(param.menu, 'myHome') ? 'active' : ''}">
                <a href="/home">
                    <i class="fa fa-home"></i> <span>系统首页</span>
                </a>
            </li>

            <!-- 客户管理 -->
            <li class="treeview ${fn:startsWith(param.menu, 'customer_') ? 'active' : ''}">
                <a href="#">
                    <i class="fa fa-address-book-o"></i> <span>客户管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu" >
                    <li class="${param.menu == 'customer_my' ? 'active' : ''}"><a href="/customer/my" ><i class="fa fa-circle-o"></i> 我的客户</a></li>
                    <li class="${param.menu == 'customer_public' ? 'active' : ''}"><a href="/customer/public"><i class="fa fa-circle-o"></i> 公海客户</a></li>
                </ul>
            </li>

            <!-- 工作记录 -->
            <li class="treeview ${fn:startsWith(param.menu, 'sale_') ? 'active' : ''}">
                <a href="#">
                    <i class="fa fa-bars"></i> <span>工作记录</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.menu == 'sale_chance' ? 'active' : ''}"><a href="/sale/my/chance"><i class="fa fa-circle-o"></i> 我的记录</a></li>
                    <%--<li><a href="/recode/public"><i class="fa fa-circle-o"></i> 公共记录</a></li>--%>
                </ul>
            </li>
            <!-- 待办事项 -->
            <li class="treeview ${fn:startsWith(param.menu, 'task') ? 'active' : ''}">
                <a href="#">
                    <i class="fa fa-calendar"></i> <span>待办事项</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.menu == 'task' ? 'active' : ''}"><a href="/task"><i class="fa fa-circle-o"></i> 待办列表</a></li>
                </ul>
            </li>
            <!-- 统计报表   customer_charts-->
            <li class="treeview ${param.menu == 'charts' ? 'active' : ''}">
                <a href="#">
                    <i class="fa fa-pie-chart"></i> <span>统计报表</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>

                <ul class="treeview-menu ">
                    <li class="${param.menu == 'charts' ? 'active' : ''}"><a href="/chart"><i class="fa fa-circle-o"></i> 客户统计</a></li>
                </ul>
            </li>


            <li class="${param.menu == 'disk' ? 'active' : ''}"><a href="/disk"><i class="fa fa-share-alt"></i> <span>公司网盘</span></a></li>
            <li class="header">系统管理</li>
            <!-- 部门员工管理 -->
            <li class="${param.menu == 'home' ? 'active' : ''}"><a href="/employee"><i class="fa fa-users"></i> <span>员工管理</span></a></li>

            <!--<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
