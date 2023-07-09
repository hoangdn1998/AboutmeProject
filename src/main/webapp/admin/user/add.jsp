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
		    String err = request.getParameter("err");
		  	if("1".equals(err)){
		    	out.print("<span style=\"background: #33CCFF; color: white; font-weight: bold; padding: 5px; \">Thêm người dùng thất bại!</span>");
		  	}
		  	if("2".equals(err)){
		    	out.print("<span style=\"background: #33CCFF; color: white; font-weight: bold; padding: 5px; \">Username đã tồn tại</span>");
		  	}
    		%>
              <div class="card-header">
                <h5 class="card-title">Add</h5>
              </div>
              <div class="card-body">
                <form id="user-form" action="<%=request.getContextPath()%>/admin/user/add" method="post" enctype="multipart/form-data" >
                  <div class="row">
                    <div class="col-md-10 px-1">
                      <div class="form-group">
                        <label>Nhập tên người dùng</label>
                        <input type="text" class="form-control" id ="fullname" placeholder="Nhập tên người dùng" name="fullname" />
                        <label for="fullname" class="error"></label>
                        <label>Nhập username</label>
                        <input type="text" class="form-control" id ="username" placeholder="Nhập tên username" name="username" />
                        <label for="username" class="error"></label>
                        <label>Nhập password</label>
                        <input type="password" class="form-control" id ="password" placeholder="Nhập tên password" name="password" />
                        <label for="password" class="error"></label>
                        <label>Nhập Hình ảnh</label>
                        <input type="file" name="picture" id="picture"/>
                        <label for="picture" class="error"></label>
                      </div>
                    </div>
                    <div class="col-md-2 px-1">
                    <div class="update ml-auto mr-auto">
                      <input type="submit" class="btn btn-primary btn-round" value="Thêm" />
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
			password : {
			    required:true,
			},
			picture: {
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
			password : {
				required:'Vui lòng nhập mật khẩu!',
			},
			picture: {
                required:"Vui lòng nhập ảnh",
         	},
		},
	});
});
</script>
 <%@ include file="../../templates/admin/inc/footer.jsp"%>