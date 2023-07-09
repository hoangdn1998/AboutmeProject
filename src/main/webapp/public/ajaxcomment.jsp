<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
	<%	
	Comment commentItem = (Comment) request.getAttribute("commentItem");
	if(commentItem != null){%>
	<div class="cmt-user">
	<div class="cmt-left">
		<div class="cmt-img">
			<img style="width: 50px;height: 40px;" src="<%=request.getContextPath()%>/templates/public/assets/img/boy.gif" alt="user"/>
		</div>
		<div  class="cmt-name" >
			<p><%=commentItem.getName()%> </p>
		</div>
	</div>
	<div class="cmt-right">
		<p class="user-commented"><%=commentItem.getComment()%></p>
		<p class="date-comment"><%=commentItem.getDate_create()%></p>
	</div>	
	<div class="clr"></div>
	</div>
	<%}%>