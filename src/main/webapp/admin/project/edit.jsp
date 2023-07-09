<%@page import="model.bean.Project"%>
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
            Project project = (Project)request.getAttribute("project");
            String name = request.getParameter("name");
    		String preview = request.getParameter("preview");
    		String link = request.getParameter("link");
		    String err = request.getParameter("err");
		    String picture = "";
		    if(project != null){
		    	name = project.getName();
		    	preview = project.getPreview();
		    	link = project.getLink();
		    	picture = project.getPicture();
		    	
		    }
		  	if("1".equals(err)){
		    	out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Thêm dự án thất bại!</span>");
		  	}
    		%>
              <div class="card-header">
                <h5 class="card-title">Sửa dự án</h5>
              </div>
              <div class="card-body">
                <form id="project-form" action="<%=request.getContextPath()%>/admin/project/edit?id=<%=project.getProject_id()%>" method="post" enctype="multipart/form-data" >
                  <div class="row">
                    <div class="col-md-10 px-1">
                      <div class="form-group">
                        <label>Nhập tên dự án</label>
                        <input type="text" class="form-control" value="<%if(name != null) out.print(name);%>" id ="name" placeholder="Nhập tên dự án" name="name" />
                        <label for="name" class="error"></label>
                        <label>Nhập preview </label>
                        <textarea class="form-control" id ="preview" name="preview"><%if(preview != null) out.print(preview);%></textarea>
						<label for="preview" class="error"></label>
						<label>Nhập link dự án</label>
                        <input type="text" class="form-control" value="<%if(link != null) out.print(link);%>" id ="form-user" placeholder="Nhập link" id="link" name="link" />
                        <label for="link" class="error"></label>
                        <label>Nhập Hình ảnh</label>
                        <input type="file" name="picture" id="picture"  />
                         <%if(picture != null && !"".equals(picture)){ %>
		                 	<br />
		                  	<label>Ảnh cũ</label><br />
		                   	<img width="200px" height="200px" src="<%=request.getContextPath()%>/upload/<%=picture%>" alt="<%=picture%>" /><br /><br />
		                   <%}else if ("".equals(picture)){%>
		                   <label>Ảnh cũ không tồn tại!</label><br />
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
	$('#project-form').validate({
		rules : {
			name : {
				required:true,
				minlength: 2,
				maxlength: 100,
			},
			preview : {
				required:true,
			},
			link : {
			    required:true,
			},
		},
		messages:{
			name : {
				required:'Vui lòng nhập tên!',
				minlength:'Nhập tối thiểu 2 kí tự',
				maxlength:'Nhập tối đa 100 kí tự',
			},
			preview : {
				required:'Vui lòng nhập mô tả dự án!',
			},
			link : {
				required:'Vui lòng nhập Link!',
			},
		},
	});
});
</script>
 <%@ include file="../../templates/admin/inc/footer.jsp"%>