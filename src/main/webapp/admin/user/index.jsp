<%@page import="model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../templates/admin/inc/header.jsp"%>
<body class="">
  <div class="wrapper ">
    <div class="sidebar" data-color="white" data-active-color="danger">
      <%@ include file="../../templates/admin/inc/leftbar.jsp"%>
    </div>
    <div class="main-panel">
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">
        <div class="container-fluid">
          <div class="navbar-wrapper">
            <div class="navbar-toggle">
              <button type="button" class="navbar-toggler">
                <span class="navbar-toggler-bar bar1"></span>
                <span class="navbar-toggler-bar bar2"></span>
                <span class="navbar-toggler-bar bar3"></span>
              </button>
            </div>
            <a class="navbar-brand" href="javascript:;">Quản lý người dùng</a>
          </div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end" id="navigation">
            <form action="<%=request.getContextPath()%>/admin/user/search" method="get">
              <div class="input-group no-border">
                <input name="search" type="text" value="" class="form-control" placeholder="Search...">
                <div class="input-group-append">
                  <div class="input-group-text">
                    <i class="nc-icon nc-zoom-split"></i>
                  </div>
                </div>
              </div>
            </form>
              <ul class="navbar-nav">
              <li class="nav-item btn-rotate dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="nc-icon nc-settings-gear-65"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/logout">logout</a>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <!-- End Navbar -->
      <div class="content">
      <%
       String msg = request.getParameter("msg");
       String err = request.getParameter("err");
	   	if("1".equals(msg)){
	   		out.print("<span style=\"background: #33CCFF; color: white; font-weight: bold; padding: 5px; \">Thêm người dùng thành công!</span>");
	   	}
	   	if("2".equals(msg)){
	   		out.print("<span style=\"background: #33CCFF; color: white; font-weight: bold; padding: 5px; \">Sửa người dùng thành công!</span>");
	   	}
	   	if("3".equals(msg)){
	   		out.print("<span style=\"background: #33CCFF; color: white; font-weight: bold; padding: 5px; \">Xóa người dùng thành công!</span>");
	   	}
	   	if("1".equals(err)){
	   		out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Id không tồn tại!</span>");
	   	}
	   	if("2".equals(err)){
	   		out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Có lỗi xảy ra!</span>");
	   	}
	   	if("3".equals(err)){
	   		out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Không phải admin!</span>");
	   	}
		if("4".equals(err)){
	   		out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \"Người dùng k tồn tại!</span>");
	   	}
		if("5".equals(err)){
	   		out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Không thể xóa admin!</span>");
	   	}
      %>
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <a href="<%=request.getContextPath()%>/admin/user/add" class="btn btn-primary btn-round" >Thêm</a>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table">
                    <thead class=" text-primary">
                    <tr>
                      <th>
                        <span>Id</span>
                      </th>
                      <th>
                        <span>UserName</span>
                      </th>
                      <th>
                        <span>Họ Tên</span>
                      </th>
                      <th>
                        <span>Hình Ảnh</span>
                      </th>
                      <th class="text-right">
                        <span>Edit</span>
                      </th>
                      <tr>
                    </thead>
                    <tbody>
                    <%
                    	@SuppressWarnings("unchecked")
                    	ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
                    	User userLogin = (User)session.getAttribute("userLogin");
                    	if(users != null){
                    		for(User item : users){
                    %>
                      <tr>
                      	<td>
                          <%=item.getUser_id()%>
                        </td>
                        <td>
                          <%=item.getUsername()%>
                        </td>
                        <td>
                         <%=item.getFullname()%>
                        </td>
                        <td>
                          <%if(item.getPicture().isEmpty()){ %>
                             Chưa có hình ảnh
                          <%} else{%>
						  <img width="200px" height="200px" src="<%=request.getContextPath()%>/upload/<%=item.getPicture()%>" alt="<%=item.getFullname()%>"/>
                          <%} %>
                        </td>
                        <% if("admin".equals(userLogin.getUsername())){
                           if(item.getUser_id() == userLogin.getUser_id()) {
                        %>
                        <td class="text-right">
                           <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=item.getUser_id()%>" class="btn btn-primary btn-round">Sửa</a>
                        </td>
                        <%}else{ %>
                        <td class="text-right">
                           <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=item.getUser_id()%>" class="btn btn-primary btn-round">Sửa</a>
                           <a href="<%=request.getContextPath()%>/admin/user/del?id=<%=item.getUser_id()%>" class="btn btn-primary btn-round">Xóa</a>
                        </td>
                        <% }}else{%>
                        <td class="text-right">
                        	<%if(userLogin.getUser_id() == item.getUser_id()){%>
                           <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=item.getUser_id()%>" class="btn btn-primary btn-round">Sửa</a>
                        	<%}}%>
                        </td>
                        <%}}%>
                    </tbody>
                  </table>
                </div>
                 <%
                int numberOfPages = (int) request.getAttribute("numberOfPages");
                int numberOfItems = (int) request.getAttribute("numberOfItems");
                int currentPage = (int) request.getAttribute("currentPage");
                if(users != null && users.size() >0){
                %>
                <div class="row">
                <div class="col-sm-6">
                </div>
                <div class="col-sm-6" style="text-align: right;">
	                <ul class="pagination">
		                 <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/users?page=<%=currentPage-1%>">Trang trước</a></li>
		                  <% 
			                 String active = "";
			                 for(int i=1; i <= numberOfPages; i++){
		                 	 if(currentPage == i){
		                 		active = "active";
		                 	 }else{
		                 		active = "";
		                 	 }
		                 %>
		                 <li class="paginate_button <%=active%>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/users?page=<%=i%>"><%=i%></a></li>
						<%} %>
		                 <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/users?page=<%=currentPage+1%>">Trang tiếp</a></li>
	             	</ul>
                </div>
                </div>
                 <%} %>
              </div>
            </div>
          </div>
        </div>
      </div>
 <script>
    document.getElementById("user").classList.add('active-menu');
 </script>
<%@ include file="../../templates/admin/inc/footer.jsp"%>