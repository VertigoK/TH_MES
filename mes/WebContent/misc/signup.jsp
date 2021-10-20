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
	<title>Company</title>
	<style>
		.content { width: 100%; }
		body { background-image: #34495e; }
		.SignupForm {
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
		.SignupForm h2 {
		 text-aligh: center;
		 margin: 30px;
		}
		.idForm, .passForm, .nameForm {
		  border-bottom: 2px solid #adadad;
		  margin: 30px;
		  padding: 10px 10px;
		}
		.id, .pw, .nm {
		  width: 100%;
		  border:none;
		  outline:none;
		  color: #636e72;
		  font-size:16px;
		  height:25px;
		  background: none;
		}
		.button .btn {
		  position: relative;
		  left: 13%;
		  transform: translateX(-50%);
		  width: 78px;
		  height:40px;
		  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
		  background-size: 200%;
		  color:white;
		  font-weight: bold;
		  border:none;
		  transition: 0.4s;
		}
		.content .btn:hover {
		  background-position: right;
		}
	</style>
</head>
<body>
   <div id="header">
      <jsp:include page="/layout/header.jsp" />
   </div>
   <div class="content">
      <c:choose>
         <c:when test="<%= member != null %>">   
            <a href="/logOut" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
            <a href="/" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
         </c:when>
         <c:otherwise>
            <div class="container">
               <form action="/signUp" class="SignupForm" method="post">
            	<h2>Sign Up</h2>
                  <div class="idForm">
                     <input name="id" type="text" class="id" placeholder="ID" required/>
                  </div>
                  <div class="passForm">
                     <input name="pw" type="password" class="pw" placeholder="PASSWORD" required/>
                  </div>
                  <div class="nameForm">
                     <input name="nm" type="text" class="nm" placeholder="NAME" required/>
                  </div>
                  <div class="button">
                     <a href="/" class="btn btn-outline-primary"><i class="fas fa-home fa-lg"></i></a>
                     <input type="reset" value="Reset" class="btn btn-outline-success"/>
                     <input type="submit" value="Sign up" class="btn btn-outline-danger"/>
                  </div>
               </form>
            </div>      
         </c:otherwise>
      </c:choose>
   </div>
</body>
</html>
