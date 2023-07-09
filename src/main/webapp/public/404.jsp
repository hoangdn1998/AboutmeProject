<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/assets/css/404.css" />
</head>
<body>
	<div class="container">
		<h2>Oops! Page Not Found</h2>
		<h1>404</h1>
		<p><span>Không tìm thấy trang</span><p>
		<a href="<%=request.getContextPath()%>/index">Đến trang chủ</a>
	</div>
</body>
</html>