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
	href="<%=request.getContextPath()%>/templates/public/assets/css/style.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/templates/public/assets/css/responsive.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

	<header id="masthead" class="site-header" data-anchor-target=".hero"
		data-top="background: rgba(255,255,255,0); padding: 30px 0; box-shadow: 0px 0px 20px 6px rgba(0, 0, 0, 0);"
		data-top-bottom="background: rgba(255,255,255,1); padding: 10px 0; box-shadow: 0px 0px 20px 6px rgba(0, 0, 0, 0.2);">
		<nav id="primary-navigation" class="site-navigation">
			<div class="container">
				<div class="navbar-header page-scroll">

					<button type="button" class="navbar-toggle collapsed"
						data-target="#portfolio-perfect-collapse" aria-expanded="false">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

					<a href="#hero" class="site-logo"><img style="width: 220px; height: 60px"
						src="<%=request.getContextPath()%>/templates/public/assets/img/hoang.png"
						alt="logo"></a>

				</div>
				<!-- /.navbar-header -->
				<div class="main-menu" id="portfolio-perfect-collapse">

					<ul class="nav navbar-nav navbar-right">

						<li class="page-scroll"><a href="#hero">Home</a></li>
						<li class="page-scroll"><a href="#about">Giới thiệu</a></li>
						<li class="page-scroll"><a href="#service">Mục tiêu</a></li>
						<li class="page-scroll"><a href="#portfolio">Dự án</a></li>
						<li class="page-scroll"><a href="#news">Tin tức</a></li>
						<li class="page-scroll"><a href="#contact">Liên hệ</a></li>

					</ul>
					<!-- /.navbar-nav -->

				</div>
				<!-- /.navbar-collapse -->

			</div>
		</nav>
		<!-- /.primary-navigation -->
	</header>
	<!-- /#header -->