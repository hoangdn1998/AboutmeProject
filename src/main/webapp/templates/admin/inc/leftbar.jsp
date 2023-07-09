<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="logo">
        <a href="javascript:void(0)" class="simple-text logo-mini">
          <div class="logo-image-small">
            <img src="<%=request.getContextPath()%>/templates/admin/assets/img/logo-small.png">
          </div>
          <!-- <p>CT</p> -->
        </a>
        <a href="javascript:void(0)" class="simple-text logo-normal">
          Creative Hoàng
          <!-- <div class="logo-image-big">
            <img src="<%=request.getContextPath()%>/templates/admin/assets/img/logo-big.png">
          </div> -->
        </a>
      </div>
      <div class="sidebar-wrapper">
        <ul class="nav">
          <li id="main">
            <a href="<%=request.getContextPath()%>/admin/index">
              <i class="nc-icon nc-bank"></i>
              <p>Trang chủ</p>
            </a>
          </li>
          <li id="admin">
            <a href="<%=request.getContextPath()%>/admin/profile">
              <i class="nc-icon nc-single-02"></i>
              <p>Thông tin admin</p>
            </a>
          </li>
          <li id="cat">
            <a href="<%=request.getContextPath()%>/admin/cats">
              <i class="nc-icon nc-badge"></i>
              <p>Quản lý Danh mục</p>
            </a>
          </li>
          <li id="user">
            <a href="<%=request.getContextPath()%>/admin/users">
              <i class="nc-icon nc-tile-56"></i>
              <p>Quản lý nguời dùng</p>
            </a>
          </li>
          <li id="inf">
            <a href="<%=request.getContextPath()%>/admin/news">
              <i class="nc-icon nc-paper"></i>
              <p>Quản lý Tin tức</p>
            </a>
          </li>
          
          <li id="contact">
            <a href="<%=request.getContextPath()%>/admin/contacts">
              <i class="nc-icon nc-chat-33"></i>
              <p>Quản lý liên hệ</p>
            </a>
          </li>
           <li id="project">
            <a href="<%=request.getContextPath()%>/admin/projects">
              <i class="nc-icon nc-tv-2"></i>
              <p>Quản lý dự án</p>
            </a>
          </li>
        </ul>
      </div>