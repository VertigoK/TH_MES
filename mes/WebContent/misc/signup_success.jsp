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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/lv1StyleSheet.css" />
    <style>
    	.item {
    		display: grid;
    		grid-template-columns: repeat(5, 1fr);
    		grid-template-rows: repeat(5, 1fr);
    		background-color: white;    	
    	}
    	.member {
    		grid-column: 2 / span 3;
			grid-row: 3;
    	}
    </style>
    <title>Company</title>
</head>
<body>
	<c:if test="<%= member == null %>">
		<c:redirect url="/" />
	</c:if>
	<div id="header">
	   <jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
	   <jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content" align="center">
		<div class="item">
			<c:choose>
				<c:when test="<%= member != null %>">	
					<div class="member">
						<h3><%=member.getMem_nm()%>님의 가입을 환영합니다!</h3>
					</div>
				</c:when>
			</c:choose>		
		</div>
	</div>
</body>
</html>


 
 