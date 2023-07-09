<%@page import="util.StringUtil"%>
<%@page import="model.bean.News"%>
<%@page import="model.bean.Project"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<footer><p>&copy;AboutMe - Một dự án tại VinaEnter Edu | Thực hiện bởi <a href="">Như Hoàng</a></p></footer>
<!-- Modals -->
		<%
		@SuppressWarnings("unchecked")
		ArrayList<Project> itemProjects = (ArrayList<Project>) request.getAttribute("projects");
		if(itemProjects != null){
			int i = 0;
			for(Project item:itemProjects){
				++i;
		%>
<div id="portfolioProjectItem<%=i%>" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<a class="close" data-dismiss="modal"><span
					class="glyphicon glyphicon-remove"></span></a> <img class="img-res"
					src="<%=request.getContextPath()%>/upload/<%=item.getPicture()%>"
					alt="">
			</div>
			<div class="modal-body">
				<h4 class="modal-title"><%=item.getName()%></h4>
				<p><%=item.getName()%></p>
			</div>
			<div class="modal-footer">
				<a href="<%=item.getLink()%>" class="btn btn-fill">Xem</a>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<%}} %>
<!-- /.modal -->
				<%
				@SuppressWarnings("unchecked")
				ArrayList<News> itemNews = (ArrayList<News>) request.getAttribute("news");
				if(itemNews != null){
					int i = 0;
					for(News item:itemNews){
						++i;
				%>
<div id="portfolioItem<%=i%>" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<a class="close" data-dismiss="modal"><span
					class="glyphicon glyphicon-remove"></span></a> <img class="img-res"
					src="<%=request.getContextPath()%>/upload/<%=item.getPicture()%>"
					alt="">
			</div>
			<div class="modal-body">
				<h4 class="modal-title"><%=item.getName()%></h4>
				<p><%=item.getPreview()%></p>
			</div>
			<div class="modal-footer">
				<a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(item.getName())%>/<%=StringUtil.makeSlug(item.getPreview())%>-<%=item.getNews_id()%>" class="btn btn-fill">Xem tin tức</a>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<%}} %>
<!-- /.modal -->

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/templates/public/assets/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script
	src="<%=request.getContextPath()%>/templates/public/assets/js/skrollr.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-progressbar/0.9.0/bootstrap-progressbar.min.js"></script>
<script
	src="<%=request.getContextPath()%>/templates/public/assets/js/jquery.countTo.min.js"></script>
<script
	src="<%=request.getContextPath()%>/templates/public/assets/js/script.js"></script>
</body>
</html>