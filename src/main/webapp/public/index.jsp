<%@page import="util.StringUtil"%>
<%@page import="model.bean.News"%>
<%@page import="model.bean.Project"%>
<%@page import="model.bean.Target"%>
<%@page import="model.bean.Skill"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Infomation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../../templates/public/inc/header.jsp"%>
<div id="hero" class="hero">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h1>Lã Như Hoàng</h1>
				<div class="page-scroll">
					<p class="job-title">Chào mừng đến với website cá nhân của mình</p>
					<a href="#contact" class="btn btn-fill ">Liên hệ tôi</a>
					<div class="clearfix visible-xxs"></div>
					<a href="#about" class="btn btn-border">Tìm hiểu thêm một chút
						về mình nhé!</a>
				</div>
				<div>
					<%
					Infomation inf = (Infomation) request.getAttribute("inf");
					String msg = request.getParameter("msg");
					String err = request.getParameter("err");
					if ("1".equals(msg)) {
						out.print(
						"<span style=\"background:#33CC33; color: white; font-weight: bold; padding: 5px; \">Cảm ơn đã liên hệ cho tôi!</span>");
					}
					if ("1".equals(err)) {
						out.print(
						"<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Quá trình xử lý xảy ra lỗi xin thử lại!</span>");
					}
					%>
				</div>
			</div>

			<div class="col-md-6 text-right">
				<img
					src="<%=request.getContextPath()%>/upload/<%=inf.getUser().getPicture()%>"
					alt="nhu-hoang">
			</div>

		</div>
	</div>
</div>
<!-- /.hero -->

<main id="main" class="site-main">

	<section id="about" class="site-section section-about text-center">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<h2>Giới thiệu</h2>
					<img
						src="<%=request.getContextPath()%>/templates/public/assets/img/lines.svg"
						class="img-lines" alt="lines">
					<p><%=inf.getProfile()%></p>
					<a href="https://drive.google.com/file/d/1eGr8pH_J6bdmurSdzz7IgMR2enJINuBY/view?usp=share_link"
						class="btn btn-fill" target="_blank" >Tải cv của tôi</a>
				</div>
			</div>
		</div>
	</section>
	<!-- /.secton-about -->

	<section class="site-section section-skills">
		<div class="container">
			<div class="text-center">
				<h3>Kỹ năng</h3>
				<img
					src="<%=request.getContextPath()%>/templates/public/assets/img/lines.svg"
					class="img-lines" alt="lines">
			</div>
			<div class="row">
				<%
				@SuppressWarnings("unchecked")
				ArrayList<Skill> skills = (ArrayList<Skill>) request.getAttribute("skills");
				if (skills != null) {
					for (Skill item : skills) {
				%>
				<div class="col-md-4">
					<div class="skill">
						<h4><%=item.getName()%></h4>
						<div class="progress">
							<div class="progress-bar" role="progressbar"
								data-transitiongoal="<%=item.getSkill()%>"></div>
							<!-- /.progress-bar -->
						</div>
						<!-- /.progress -->
					</div>
					<!-- /.skill -->
				</div>
				<%
				}
				}
				%>
			</div>
		</div>
	</section>
	<!-- /.secton-skills -->

	<section id="service"
		class="site-section section-services overlay text-center">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h3>Mục tiêu</h3>
					<img
						src="<%=request.getContextPath()%>/templates/public/assets/img/lines.svg"
						class="img-lines" alt="lines">
				</div>
				<%
				@SuppressWarnings("unchecked")
				ArrayList<Target> targets = (ArrayList<Target>) request.getAttribute("targets");
				if (targets != null) {
					for (Target item : targets) {
				%>
				<div class="col-sm-4">
					<div class="service">
						<h4><%=item.getTargetname()%></h4>
						<p><%=item.getTarget()%></p>
					</div>
					<!-- /.service -->
				</div>
				<%
				}
				}
				%>
			</div>
		</div>
	</section>
	<!-- /.secton-services -->

	<section id="portfolio" class="site-section section-portfolio">
		<div class="container">
			<div class="text-center">
				<h3>Dự án gần đây:</h3>
				<img
					src="<%=request.getContextPath()%>/templates/public/assets/img/lines.svg"
					class="img-lines" alt="lines">
			</div>
			<div class="row">
				<%
				@SuppressWarnings("unchecked")
				ArrayList<Project> projects = (ArrayList<Project>) request.getAttribute("projects");
				if (projects != null) {
					int i = 0;
					for (Project item : projects) {
						++i;
				%>
				<div class="col-md-4 col-xs-6">
					<div class="portfolio-item">
						<img
							src="<%=request.getContextPath()%>/upload/<%=item.getPicture()%>"
							class="img-res" alt="">
						<div class="portfolio-item-info">
							<h4><%=item.getName()%></h4>
							<a href="#" data-toggle="modal"
								data-target="#portfolioProjectItem<%=i%>"><span
								class="glyphicon glyphicon-eye-open"></span></a> <a
								href="<%=item.getLink()%>"><span
								class="glyphicon glyphicon-link"></span></a>
						</div>
						<!-- /.portfolio-item-info -->
					</div>
					<!-- /.portfolio-item -->
				</div>
				<%
				}
				}
				%>
			</div>
		</div>
	</section>
	<section id="news" class="site-section section-news">
		<div class="container">
			<div class="text-center">
				<h3>Tin tức</h3>
				<img
					src="<%=request.getContextPath()%>/templates/public/assets/img/lines.svg"
					class="img-lines" alt="lines">
			</div>
			<div class="row">
				<%
				@SuppressWarnings("unchecked")
				ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
				if (news != null) {
					int i = 0;
					for (News item : news) {
						++i;
				%>
				<div class="col-md-4 col-xs-6">
					<div class="portfolio-item">
						<img
							src="<%=request.getContextPath()%>/upload/<%=item.getPicture()%>"
							class="img-res" alt="<%=item.getName()%>">
						<div class="portfolio-item-info">
							<h4><%=item.getPreview()%></h4>
							<a href="#" data-toggle="modal"
								data-target="#portfolioItem<%=i%>"><span
								class="glyphicon glyphicon-eye-open"></span></a> <a
								href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(item.getName())%>/<%=StringUtil.makeSlug(item.getPreview())%>-<%=item.getNews_id()%>"><span
								class="glyphicon glyphicon-link"></span></a>
						</div>
						<!-- /.portfolio-item-info -->
					</div>
					<!-- /.portfolio-item -->
				</div>
				<%
				}
				}
				%>
			</div>
		    <%
                int numberOfPages = (int) request.getAttribute("numberOfPages");
                int numberOfItems = (int) request.getAttribute("numberOfItems");
                int currentPage = (int) request.getAttribute("currentPage");
                if(projects != null && projects.size() >0){
                %>
                <div class="row">
                <div class="col-sm-12" style="text-align: center;">
	                <ul class="pagination">
		                 <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/index?page=<%=currentPage-1%>#news">Trang trước</a></li>
		                  <% 
			                 String active = "";
			                 for(int i=1; i <= numberOfPages; i++){
		                 	 if(currentPage == i){
		                 		active = "active";
		                 	 }else{
		                 		active = "";
		                 	 }
		                 %>
		                 <li class="paginate_button <%=active%>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/index?page=<%=i%>#news"><%=i%></a></li>
						<%} %>
		                 <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/index?page=<%=currentPage+1%>#news">Trang tiếp</a></li>
	             	</ul>
                </div>
                </div>
                 <%} %>
		</div>
	</section>
	<!-- /.secton-portfolio -->

	<section class="site-section section-counters text-center">
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-xs-12">
					<p class="counter start" data-to="<%=inf.getYear()%>"
						data-speed="2000">0</p>
					<h4>Số năm kinh nghiệm</h4>
				</div>
				<div class="col-sm-4 col-xs-12">
					<p class="counter start" data-to="<%=inf.getNumOfProject()%>"
						data-speed="2000">0</p>
					<h4>Số dự án đã tham gia thực hiện</h4>
				</div>
				<div class="col-sm-4 col-xs-12">
					<p id="infinity" class="counter" data-from="0" data-to="1"
						data-speed="1000">0</p>
					<h4>Thời gian bạn online</h4>
				</div>
			</div>
		</div>
	</section>
	<!-- /.section-counters -->

	<section id="contact" class="site-section section-form text-center">
		<div class="container">

			<h3>Contact</h3>
			<img
				src="<%=request.getContextPath()%>/templates/public/assets/img/lines.svg"
				class="img-lines" alt="lines" />

			<form action="<%=request.getContextPath()%>/admin/contact/add"
				method="get">
				<div class="row">
					<div class="col-sm-6">
						<input type="text" name="name" class="form-control mt-x-0"
							placeholder="Tên" required>
					</div>
					<div class="col-sm-6">
						<input type="email" name="email" class="form-control"
							placeholder="Email" required>
					</div>
					<div class="col-sm-6">
						<input type="text" name="phone" class="form-control mt-x-0"
							placeholder="Số điện thoại: " required>
					</div>
					<div class="col-sm-6">
						<input type="text" name="address" class="form-control"
							placeholder="Địa chỉ: " required>
					</div>
					<div class="col-sm-12">
						<textarea name="message" id="message" class="form-control"
							placeholder="Message" required></textarea>
					</div>
				</div>
				<button class="btn btn-border" type="submit">
					Liên hệ tôi <span class="glyphicon glyphicon-send"></span>
				</button>
			</form>
		</div>
	</section>
	<!-- /.section-form -->

</main>
<!-- /#main -->
<%@ include file="../../templates/public/inc/footer.jsp"%>