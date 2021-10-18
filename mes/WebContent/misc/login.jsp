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
    <link rel="stylesheet" type="text/css" href="./style.css">
   	<title>Company</title>
    <style>
       	.content { width: 100%; }
		body {
		  background-image: #34495e;
		}
		.loginForm {
		  position:absolute;
		  width:300px;
		  height:400px;
		  padding: 30px, 20px;
		  background-color:#FFFFFF;
		  text-align:center;
		  top:50%;
		  left:50%;
		  transform: translate(-50%,-50%);
		  border-radius: 15px;
		}
		.loginForm h2{
		  text-align: center;
		  margin: 30px;
		}
		.idForm{
		  border-bottom: 2px solid #adadad;
		  margin: 30px;
		  padding: 10px 10px;
		}
		.passForm{
		  border-bottom: 2px solid #adadad;
		  margin: 30px;
		  padding: 10px 10px;
		}
		.id {
		  width: 100%;
		  border:none;
		  outline:none;
		  color: #636e72;
		  font-size:16px;
		  height:25px;
		  background: none;
		}
		.pw {
		  width: 100%;
		  border:none;
		  outline:none;
		  color: #636e72;
		  font-size:16px;
		  height:25px;
		  background: none;
		}
		.content .btn {
		  position:relative;
		  left:40%;
		  transform: translateX(-50%);
		  margin-bottom: 40px;
		  width:80%;
		  height:40px;
		  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
		  background-position: left;
		  background-size: 200%;
		  color:white;
		  font-weight: bold;
		  border:none;
		  cursor:pointer;
		  transition: 0.4s;
		  display:inline;
		}
		.content .btn:hover {
		  background-position: right;
		}
		.bottomText {
		  text-align: center;
		}
	</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div class="content" align="center">
		<br>
		<c:choose>
			<c:when test="<%= member != null %>">	
				<a href="/logOut" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
			</c:when>
			<c:otherwise>
				<div class="container" align="center" style="width:364px">
					<form action="/logIn" method="post"  class="loginForm">	
						<h2>Login</h2>
				     	<div class="idForm">
				        	<input name="id" type="text" class="id" placeholder="ID" required>
				      	</div>
					  	<div class="passForm">
					  		<input name="pw" type="password" class="pw" placeholder="PW">
					  	</div>
						<div>
					       <input type="submit" class="btn btn-primary" value="Log in"/>
					    </div>
					    <div class="bottomText">
					        Don't you have an ID? <a href="/signUpForm">Sign up</a>
					  	</div>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>





















