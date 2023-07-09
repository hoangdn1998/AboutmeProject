<%@page import="model.bean.Contact"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.News"%>
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
            Contact contact = (Contact) request.getAttribute("contact");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
    		String email = request.getParameter("email");
    		String address = request.getParameter("address");
    		String message = request.getParameter("message");
    		String err = request.getParameter("err");
    		if(contact != null){
    			name = contact.getContact_name();
    			phone = contact.getPhone();
    			email = contact.getEmail();
    			address = contact.getAddress();
    			message = contact.getMessage();
    		}
    		if("1".equals(err)){
		    	out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Sửa liên hệ thất bại!</span>");
		  	}
    		%>
              <div class="card-header">
                <h5 class="card-title">Sửa Liên hệ</h5>
              </div>
              <div class="card-body">
                <form id="contact-form" action="<%=request.getContextPath()%>/admin/contact/edit?id=<%=contact.getContact_id()%>" method="post" >
                  <div class="row">
                    <div class="col-md-10 px-1">
                      <div class="form-group">
                        <label>Nhập tên </label>
                        <input type="text" name="name" value="<%if(name != null) out.print(name);%>" class="form-control" id ="name" placeholder="Nhập tên"/>
                        <label for="name" class="error"></label>
                        <label>Nhập số điện thoại </label>
                        <input type="text" name="phone" value="<%if(phone != null) out.print(phone);%>" class="form-control" id ="phone" placeholder="Nhập số điện thoại"/>
                      	<label for="phone" class="error"></label>
                        <label>Nhập email </label>
                        <input type="text" name="email" value="<%if(email != null) out.print(email);%>" class="form-control" id ="email" placeholder="Nhập email"/>
                        <label for="email" class="error"></label>
                        <label>Nhập địa chỉ </label>
                        <input type="text" name="address" value="<%if(address != null) out.print(address);%>" class="form-control" id ="address" placeholder="Nhập địa chỉ"/>
                        <label for="address" class="error"></label>
                        <label>Nhập message</label>
                        <textarea class="form-control" id ="message" name="message"><%if(message != null) out.print(message);%></textarea>
                      	<label for="message" class="error"></label>
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
	$('#contact-form').validate({
		rules : {
			name : {
				required:true,
				minlength: 2,
				maxlength: 100,
			},
			phone : {
				required:true,
			},
			email : {
				required:true,
				minlength: 10,
				maxlength: 50,
			},
			address: {
               required:true,
           },
           message: {
               required:true,
           },
			
		},
		messages:{
			name : {
				required:'Vui lòng nhập tên!',
				minlength:'Nhập tối thiểu 2 kí tự',
				maxlength:'Nhập tối đa 100 kí tự',
			},
			phone : {
				required:'Vui lòng nhập số điện thoại!',
			},
			email : {
				required:'Vui lòng nhập email!',
				minlength:'Nhập tối thiểu 10 kí tự',
				maxlength:'Nhập tối đa 50 kí tự',
			},
			address: {
                required:"Vui lòng nhập địa chỉ",
         	},
         	 message: {
         		required:"Vui lòng nhập Lời nhắn",
             },
		},
	});
});
</script>
 <%@ include file="../../templates/admin/inc/footer.jsp"%>