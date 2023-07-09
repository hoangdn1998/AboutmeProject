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
    	
          <div class="col-md-12">
            <div class="card card-user">
            <%
       	    User user = (User) request.getAttribute("user");
		    String err = request.getParameter("err");
		  	if("1".equals(err)){
		    	out.print("<span style=\"background: #33CCFF; color: white; font-weight: bold; padding: 5px; \">Thêm người dùng thất bại!</span>");
		  	}
		  	if("2".equals(err)){
		    	out.print("<span style=\"background: #33CCFF; color: white; font-weight: bold; padding: 5px; \">Username đã tồn tại</span>");
		  	}
    		%>
              <div class="card-header">
                <h5 class="card-title">Sửa</h5>
              </div>
              <div class="card-body">
                <form id="user-form" action="<%=request.getContextPath()%>/admin/user/edit?id=<%=user.getUser_id()%>" method="post" enctype="multipart/form-data" >
                  <div class="row">
                    <div class="col-md-10 px-1">
                      <div class="form-group">
                        <label>Nhập tên người dùng</label>
                        <input type="text" class="form-control" value="<%if(user != null) {out.print(user.getFullname());}%>" id ="fullname" placeholder="Nhập tên người dùng" name="fullname" />
                        <label for="fullname" class="error"></label>
                        <label>Nhập username</label>
                        <input type="text" class="form-control" value="<%if(user != null) {out.print(user.getUsername());}%>" id ="username" placeholder="Nhập username" name="username" disabled="disabled"/>
                        <label for="username" class="error"></label>
                        <label>Nhập password</label>
                        <input type="password" class="form-control" value="" id ="password" placeholder="Nhập password" name="password" />
                        <label>Nhập Hình ảnh</label>
                         <input type="file" name="picture" id="picture" />
                         <%if(user != null && !"".equals(user.getPicture())){ %>
                          <br />
                          <label for="picture">Ảnh cũ</label><br />
                          <img width="200px" height="200px" src="<%=request.getContextPath()%>/upload/<%=user.getPicture()%>" alt="<%=user.getFullname()%>" /><br /><br />
                          <%}else if ("".equals(user.getPicture())){%>
                          <label for="picture">Ảnh cũ không tồn tại!</label><br />
                          <%}%>
                      </div>
                    </div>
                    <div class="col-md-2 px-1">
                    <div class="update ml-auto mr-auto">
                      <input type="submit" class="btn btn-primary btn-round" value="Sửa" />
                    </div>
                     </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
<script type="text/javascript">
$(document).ready(function() {
	$('#user-form').validate({
		rules : {
			fullname : {
				required:true,
				minlength: 2,
				maxlength: 100,
			},
			username : {
				required:true,
			},
		},
		messages:{
			fullname : {
			    required:'Vui lòng nhập tên người dùng!',
				minlength:'Nhập tối thiểu 2 kí tự',
				maxlength:'Nhập tối đa 100 kí tự',
			},
			username : {
				required:'Vui lòng nhập tên tài khoản!',
			},
		},
	});
});
</script>
 <%@ include file="../../templates/admin/inc/footer.jsp"%>