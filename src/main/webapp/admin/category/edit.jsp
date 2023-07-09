<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../templates/admin/inc/header.jsp"%>
<body class="">
  <div class="wrapper ">
    <div class="sidebar" data-color="white" data-active-color="danger">
     <%@ include file="../../templates/admin/inc/leftbar.jsp"%>
    </div>
      <%
        	String name = request.getParameter("name");
        	String err = request.getParameter("err");
        	Category category = (Category)request.getAttribute("category");
        	if(category != null){
        		name = category.getName();
        	}
        	if("1".equals(err)){
        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px; \">Có lỗi xảy ra!</span>");
        	}
        %>
    <div class="main-panel">
          <div class="col-md-12">
            <div class="card card-user">
              <div class="card-header">
                <h5 class="card-title">Edit</h5>
              </div>
              <div class="card-body">
                <form id="cat-form" action="<%=request.getContextPath()%>/admin/cat/edit?id=<%=category.getCategory_id()%>" method="post">
                  <div class="row">
                    <div class="col-md-10 px-1">
                      <div class="form-group">
                        <label>Tên danh mục</label>
                        <input type="text" class="form-control" id ="form-user" placeholder="Nhập tên danh mục" id="category" name="category" value="<% if(name != null) {out.print(name);}%>"/>
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
	$('#cat-form').validate({
		rules : {
			category : {
				required:true,
				minlength: 6,
				maxlength: 50,
			},
		},
		messages:{
			category : {
				required:'Vui lòng nhập tên danh mục!',
				minlength: 'Nhập tối thiểu 6 kí tự',
				maxlength: 'Nhập tối đa 50 kí tự',
			},
		},
	});
});
</script>
 <%@ include file="../../templates/admin/inc/footer.jsp"%>