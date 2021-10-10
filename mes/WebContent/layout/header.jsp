<%@page import="mes.dto.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberBean member = (MemberBean) session.getAttribute("logInInfo");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		html, body {
			margin: 0;
		}
		.container-fluid {
			display: table;
			padding: 0 5px;
			width: 100%;
			height: 10px;
		}
		.left p {
			display: table-cell;
			letter-spacing: 5px;
		}
		.right {
			height: 100%;
			width: 170px;
			display: table-cell;
			text-align: right;
		}
		.right .btn {
			text-decoration: none;
			color: #0D0D0D;
			border: 1px solid #0D0D0D;
			padding: 0 8px;
			border-radius: 5px;
			background-color: white;
		}
		.btn:hover, .btn:active {
			border: 1px solid #a7a4a4;
			color: white;
			background-color: #a7a4a4;
		}
		.logout:hover span { display:none; }
		.logout:hover:after { content:'Log out'; }
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="left">
			<p>Company Name</p>
		</div>
		<div class="right">
			<c:if test="<%= member == null %>">
				<a href="/logInForm" class="btn shadow-none">Log In</a>			
			</c:if>
			<c:if test="<%= member != null %>">
				<a href="/logOut" class="btn shadow-none logout"><span><%= member.getMem_nm() %></span></a>	
			</c:if>
		</div>
	</div>
</body>
</html>