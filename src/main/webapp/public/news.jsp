<%@page import="util.StringUtil"%>
<%@page import="model.bean.Comment"%>
<%@page import="model.dao.CommentDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.News"%>
<%@page import="model.bean.Project"%>
<%@page import="model.bean.Target"%>
<%@page import="model.bean.Skill"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Infomation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>

<!-- Basic Page Needs
    ================================================== -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Hoang's website</title>

<meta name="description" content="">
<meta name="author" content="">
<meta name="keywords" content="">

<!-- Mobile Specific Metas
    ================================================== -->
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes" />

<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">

<!-- Favicon
    ================================================== -->
<link rel="apple-touch-icon" sizes="180x180"
	href="<%=request.getContextPath()%>/templates/public/assets/img/H-icon.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="<%=request.getContextPath()%>/templates/public/assets/img/H-icon.png">

<!-- Stylesheets
    ================================================== -->
<!-- Bootstrap core CSS -->
<link
	href="<%=request.getContextPath()%>/templates/public/assets/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="<%=request.getContextPath()%>/templates/public/assets/css/detail.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/templates/public/assets/css/responsive.css"
	rel="stylesheet">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>  
 <!-- Jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/templates/public/assets/js/jquery-3.2.1.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

	<header >
		<nav id="primary-navigation" class="site-navigation">
			<div class="container">
				<div class="navbar-header page-scroll">

					<button type="button" class="navbar-toggle collapsed"
						data-target="#portfolio-perfect-collapse" aria-expanded="false">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

					<a href="<%=request.getContextPath()%>/index#hero" class="site-logo"><img style="width: 220px; height: 60px"
						src="<%=request.getContextPath()%>/templates/public/assets/img/hoang.png"
						alt="logo"></a>

				</div>
				<!-- /.navbar-header -->
				<div class="main-menu" id="portfolio-perfect-collapse">

					<ul class="nav navbar-nav navbar-right">

						<li class="page-scroll"><a href="<%=request.getContextPath()%>/index#hero">Home</a></li>
						<li class="page-scroll"><a href="<%=request.getContextPath()%>/index#about">Giới thiệu</a></li>
						<li class="page-scroll"><a href="<%=request.getContextPath()%>/index#service">Mục tiêu</a></li>
						<li class="page-scroll"><a href="<%=request.getContextPath()%>/index#portfolio">Dự án</a></li>
						<li class="page-scroll"><a href="<%=request.getContextPath()%>/index#news">Tin tức</a></li>
						<li class="page-scroll"><a href="<%=request.getContextPath()%>/index#contact">Liên hệ</a></li>

					</ul>
					<!-- /.navbar-nav -->

				</div>
				<!-- /.navbar-collapse -->

			</div>
		</nav>
		<!-- /.primary-navigation -->
	</header>
	<!-- /#header -->
<div id="hero" class="hero">
		<%
		String err = request.getParameter("err");
		News newsItem = (News) request.getAttribute("newsItem");
		@SuppressWarnings("unchecked")
		ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
		@SuppressWarnings("unchecked")
		ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
		if("1".equals(err)){
	   		out.print("<span style=\"background: #33CCFF; color: red; font-weight: bold; padding: 5px; \">Id không tồn tại!</span>");
	   	}
		%>
		<div class="container-fluid">
			<div class="row" id="news">
				<div class="col-md-3" id="news_left">
					<div class="left">
						<div class="title-left">
							<h5>Bài viết xem nhiều </h5>
						</div>
						<div>
							<ul>
								<%if(news != null){
									for(News item: news){
								%>
								<li>
									<div class="img">
										<a href="<%=request.getContextPath()%>/news/detail?news_id=<%=item.getNews_id()%>"><img class="img-fluid" src="<%=request.getContextPath()%>/upload/<%=item.getPicture()%>" /></a>
									</div>
									<div class="infor">
										<p class="newsitem-icon"><i class="fa fa-pencil"></i> admin <i class="fa fa-eye"></i> Lượt đọc: <%=item.getCounter()%></p>
									</div>
									<div class="des">
										<p class="newsitem-preview"><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(item.getName())%>/<%=StringUtil.makeSlug(item.getPreview())%>-<%=item.getNews_id()%>"><%=item.getName()%></a></p>
									</div>
								</li>
								<%}} %>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-6" id="news_main">
					<div class="main">
						<div class="title-main">
							<h1 class="title-h"><%=newsItem.getCategory().getName()%></h1>
							<h2><%=newsItem.getName()%></h2>
							<div class="infor">
								<p><i class="fa fa-pencil"></i> admin <i class="fa fa-eye"></i> Lượt đọc:<%=newsItem.getCounter()%></p>
							</div>
							<div class="content"><p class="news-detail"><%=newsItem.getDetail()%></p>
							</div>
							<div class="news-comments">
								<h3 class="cmt-title">Hãy để lại ý kiến của bạn bên dưới</h3>
								<form action="javascript:void(0)"><br>
								<input type="text" id="ucmt" name="name" placeholder="Nhập tên" value=""/><br>
								<textarea id="cmt" class="input-comments" placeholder="Nhập bình luận" rows="1" cols="100 !important"></textarea><br>
								<input class="submit-cmt" id="clickBtn" type="submit"  name="submit" value="Gửi bình luận"/>
								</form>
							</div>
							<div class="user-comments" id="user-comments">
							</div>
							<%	
							    CommentDAO commentDAO = new CommentDAO();
							    int limit =3;
								ArrayList<Comment> comments = commentDAO.getItems(newsItem.getNews_id(),limit);
								if(comments != null && comments.size() >0 ){
								%>
								<h5 class="cmt-title">Bình luận liên quan:</h5>
								<%  for(Comment item:comments){%>	
								<div class="cmt-user">
									<div class="cmt-left">
										<div class="cmt-img">
											<img style="width: 50px;height: 40px;" src="<%=request.getContextPath()%>/templates/public/assets/img/boy.gif" alt="user"/>
										</div>
										<div  class="cmt-name" >
											<p><%=item.getName()%> </p>
										</div>
									</div>
									<div class="cmt-right">
										<p class="user-commented"><span><%=item.getComment()%></span></p>
										<p class="date-comment"><%=item.getDate_create()%></p>
									</div>	
									
									<div class="clr"></div>
								</div>
								<%}}%>
						</div>
					</div>
				</div>
				<div class="col-md-3" id="news_right">
				
					<div class="right">
						<div class="title-right">
							<h5>Danh mục tin</h5>
						</div>
						<div class="right-cats">
							<ul>
								<%
								if(categories != null){
									for(Category item: categories){
								%>
								
								<li>
									<div id="<%=item.getCategory_id()%>">
										<p class="right-cat"><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(item.getName())%>-<%=item.getCategory_id()%>" id="<%=item.getCategory_id()%>"><%=item.getName()%></a></p>
									</div>
								</li>
								<%}} %>
							</ul>
						</div>
					</div>
			</div>
		</div>
		</div>
</div>
<footer><p>&copy;AboutMe - Một dự án tại VinaEnter Edu | Thực hiện bởi <a href="<%=request.getContextPath()%>/index#hero">Như Hoàng</a></p></footer>
<!-- /.hero -->
<script type="text/javascript">
  $(document).ready(function() {
	  $("#clickBtn").click(function() {
		  var ucmt = $("#ucmt").val();
		  var cmt = $("#cmt").val();
		   if(ucmt == '' && cmt == ''){
				alert('Vui lòng nhập tên và bình luận');
	  		}else if(cmt == '') {
				alert('Vui lòng nhập bình luận');
				return;
			}else if(ucmt == '') {
				alert('Vui lòng nhập tên');
				return;
			}
		  if(cmt !== '' && ucmt !== ''){
		  getComment(cmt,ucmt);
		   }
	  });
	});
  
  function getComment(cmt,ucmt){
		$.ajax({
			url: '<%=request.getContextPath()%>/comments',
			type: 'GET',
			cache: false,
			data: {usercmt:ucmt,acmt:cmt, news_id:<%=newsItem.getNews_id()%>},
			success: function(data){
				$("#user-comments").html(data);
			},
			error: function (){
				alert('Có lỗi trong quá trình xử lý');
			}
		});
		return false;
	}
	  
  </script>
  <script>
    document.getElementById("<%=newsItem.getCategory().getCategory_id()%>").classList.add('active-menu');
  </script>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="<%=request.getContextPath()%>/templates/public/assets/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-progressbar/0.9.0/bootstrap-progressbar.min.js"></script>
<script
	src="<%=request.getContextPath()%>/templates/public/assets/js/jquery.countTo.min.js"></script>
</body>
</html>