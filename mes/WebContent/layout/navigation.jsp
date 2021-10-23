<%@page import="mes.dto.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src= "/js/navCssControll.js"></script>
    <style>
    	html, body {
    		margin: 0;
    		height: 100%;
    		width: 100%;
    	}
    	#tab {
    		margin: 1px 0;
    		float: left;
    		width: 50px;
    		height: 100%;
    		background-color: #092843;
/*     		background-color: #26262e; */
    	}
    	#tab a {
			display: block;
			background: transparent;
			color: white;
			margin: 0 auto;
			padding: 10px 16px;
			border: none;
			outline: none;
			text-align: left;
			text-decoration: none;
			cursor: pointer;
			transition: 0.3s;
			font-size: 17px;
		}
		#tab a:hover, #tab a.active {
			background-color: #E9F5FA;
			border-radius: 0;
			border-left-style: solid;
			border-left-color: #26262e;
			color: #26262e;
			font-weight: bold;
			overflow: hidden;
		}
		i {
			text-align: center;
			width: 100%;
		}
    </style>
     <script>
    $(function() {
    	$('[data-toggle="tooltip"]').tooltip()
    })
    </script>
</head>
<body>
	<c:if test="<%= member == null %>">
		<c:redirect url="/" />
	</c:if>
	<div id="tab">
		<a href="/" class="btn shadow-none" id="home" data-toggle="tooltip" data-placement="right" title="Home"><i class="fas fa-home"></i></a>
		<a href="/production" class="btn shadow-none" id="product" data-toggle="tooltip" data-placement="right" title="생산"><i class="fas fa-industry"></i></a> <!-- 생산 -->
		<a href="/quality" class="btn shadow-none" id="quality" data-toggle="tooltip" data-placement="right" title="품질"><i class="fas fa-award"></i></a> <!-- 품질 -->
		<a href="/equipment" class="btn shadow-none" id="equipment" data-toggle="tooltip" data-placement="right" title="설비"><i class="fas fa-tools"></i></a> <!-- 설비 -->
		<a href="/stock" class="btn shadow-none" id="stock" data-toggle="tooltip" data-placement="right" title="재고"><i class="fas fa-cubes"></i></a> <!-- 재고 -->
		<a href="/hr" class="btn shadow-none" id="hr" data-toggle="tooltip" data-placement="right" title="HR"><i class="fas fa-user-friends"></i></a> <!-- HR -->
		<a href="/order" class="btn shadow-none" id="order" data-toggle="tooltip" data-placement="right" title="주문"><i class="fas fa-shopping-cart"></i></a> <!-- 주문 -->
		<a href="javascript:window.history.back();" class="btn shadow-none" id="hidden" data-toggle="tooltip" data-placement="right" title="뒤로가기"><i class="fas fa-undo-alt"></i></a> <!-- 뒤로가기 -->
	</div>
</body>
</html>