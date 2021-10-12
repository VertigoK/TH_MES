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
	<title>Telstar-Hommel</title>
	<style>
		.content {
			width: 100%;
			grid-template-columns: repeat(5, 1fr);
			grid-template-rows: repeat(5, 1fr);
		}
		.container {
			grid-column: 3;
			grid-row: 2 / span 3;
			border-radius: 30px;
			background-color: white;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
		}
		.h2 { margin-bottom: 80px;}
	</style>
</head>
<body>
   <div id="header">
      <jsp:include page="/layout/header.jsp" />
   </div>
   <div class="content" align="center">
      <c:choose>
         <c:when test="<%= member != null %>">   
            <a href="/logOut" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
            <a href="/" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
         </c:when>
         <c:otherwise>
            <div class="container" align="center">
            	<div class="h2">
	            	<h2>Sign Up</h2>
            	</div>
               <form action="/signUp" class="form-group" method="post">
                  <div class="form-group input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                     </div>
                     <input name="id" type="text" class="form-control" placeholder="ID..." required/>
                  </div>
                  <div class="form-group input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                     </div>
                     <input name="pw" type="password" class="form-control" placeholder="PASSWORD..." required/>
                  </div>
                  <div class="form-group input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user-tag"></i></span>
                     </div>
                     <input name="nm" type="text" class="form-control" placeholder="이름..." required/>
                  </div>
                  <div class="form-group mt-md-3">
                     <a href="/" class="btn btn-outline-primary float-right login-btn"><i class="fas fa-home fa-lg"></i></a>
                     <input type="reset" value="Reset" class="btn btn-outline-success float-right login-btn mr-sm-3"/>
                     <input type="submit" value="Sign up" class="btn btn-outline-danger float-right login-btn mr-sm-3"/>
                  </div>
               </form>
            </div>      
         </c:otherwise>
      </c:choose>
   </div>
</body>
</html>




