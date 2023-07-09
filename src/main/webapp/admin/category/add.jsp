<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../templates/admin/inc/header.jsp"%>
<body class="">
  <div class="wrapper ">
    <div class="sidebar" data-color="white" data-active-color="danger">
     <%@ include file="../../templates/admin/inc/leftbar.jsp"%>
    </div>
    <%
    String err = (String) request.getParameter("err");
  	if("1".equals(err)){
    	out.print("<span style=\"background: red; color: white; font-weight: bold; padding: 5px; \">Thêm danh mục thành công!</span>");
  	}
    %>
    <div class="main-panel">
          <div class="col-md-12">
            <div class="card card-user">
              <div class="card-header">
                <h5 class="card-title">Add</h5>
              </div>
              <div class="card-body">
                <form id="cat-form" action="<%=request.getContextPath()%>/admin/cat/add" method="post">
                  <div class="row">
                    <div class="col-md-10 px-1">
                      <div class="form-group" id="form-group">
                        <label>Thêm danh mục</label>
                        <input type="text" class="form-control"  placeholder="Nhập tên danh mục" id="category" name="category" />
                      </div>
                    </div>
                    <div class="col-md-2 px-1">
                    <div class="update ml-auto mr-auto">
                      <input type="submit" class="btn btn-primary btn-round"  value="Thêm" />
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