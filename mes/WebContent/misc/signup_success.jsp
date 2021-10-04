<%@page import="mes.dto.MemberBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberBean member = (MemberBean) session.getAttribute("logInInfo");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/lv1StyleSheet.css"/>
    <style>
    	.login-btn {
    		color: black;
    		width:100px;
    	}
    	.login-btn:hover {
    		color: black;
    		background-color: #D9E1E5;
    	}
    </style>
    <title>Telstar-Hommel</title>
</head>
<body>
	<div id="header">
	   <jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
	   <jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content" align="center">
		<c:choose>
			<c:when test="<%= member != null %>">	
				<div class="jumbotron">
					<h3><%=member.getMem_nm()%>님의 가입을 환영합니다!</h3>
				</div>
			</c:when>
		</c:choose>		
	</div>
</body>
</html>


 
 