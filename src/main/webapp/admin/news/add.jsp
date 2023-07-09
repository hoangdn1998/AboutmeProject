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
            @SuppressWarnings("unchecked")
            ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories"); 
            String name = request.getParameter("name");
            String cat_id = request.getParameter("category");
    		String preview = request.getParameter("preview");
    		String detail = request.getParameter("detail");
		    String err = request.getParameter("err");
		  	if("1".equals(err)){
		    	out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Thêm tin tức thất bại!</span>");
		  	}
    		%>
              <div class="card-header">
                <h5 class="card-title">Thêm tin tức</h5>
              </div>
              <div class="card-body">
                <form id="news-form" action="<%=request.getContextPath()%>/admin/news/add" method="post" enctype="multipart/form-data" >
                  <div class="row">
                    <div class="col-md-10 px-1">
                      <div class="form-group">
                        <label>Nhập tên tin tức</label>
                        <input type="text" class="form-control" id ="title" placeholder="Nhập tin tức" value="<%if(name != null) out.print(name);%>" name="title" />
                        <label for="title" class="error"></label>
                        <label>Nhập review </label>
                        <textarea class="form-control" id ="form-user" name="preview"><%if(preview != null) out.print(preview);%></textarea>
                       	<label for="preview" class="error"></label>
                        <label >Nhập chi tiết</label>
                        <textarea class="form-control" id ="detail" name="detail"><%if(detail != null) out.print(detail);%></textarea>
                        <label for="detail" class="error"></label>
                        <label for="category">Nhập danh mục tin</label>
                           <select id="category" name="category" class="form-control" >
                             <option value="">--Chọn Danh mục--</option>
                           <%
                          		if(categories != null){
                          		for(Category item:categories){
                           %>
                            <option <%if(cat_id != null && cat_id.equals(String.valueOf(item.getCategory_id()))) out.print(" selected");%> value="<%=item.getCategory_id()%>"><%=item.getName()%></option>
                       	   <%}} %>
                           </select>
                           <label for="category" class="error"></label>
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
 <script>
 var editor = CKEDITOR.replace('detail');
 CKFinder.setupCKEditor(editor, '<%=request.getContextPath()%>/library/ckfinder/');
</script>
<script type="text/javascript">
$(document).ready(function() {
	$('#news-form').validate({
		ignore: [],
		rules : {
			title : {
				required:true,
				minlength: 2,
				maxlength: 100,
			},
			preview : {
				required:true,
				minlength: 10,
			},
			detail : {
                required: true,
               
			},
			category: {
               required:true,
            },
            picture: {
               required:true,
            },
			
		},
		messages:{
			title : {
				required:'Vui lòng nhập tên!',
				minlength:'Nhập tối thiểu 2 kí tự',
				maxlength:'Nhập tối đa 100 kí tự',
			},
			preview : {
				required:'Vui lòng nhập giới thiệu tin!',
				minlength:'Nhập tối thiểu 10 kí tự',
			},
			detail : {
				required:'Vui lòng nhập mô tả',
			},
			category: {
                required:"Vui lòng nhập danh mục tin",
         	},
         	picture: {
         		required:"Vui lòng nhập ảnh",
             },
		},
	});
});
</script>
 <%@ include file="../../templates/admin/inc/footer.jsp"%>