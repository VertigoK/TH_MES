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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/lv1StyleSheet.css"/>
    <title>s</title>
    <style>
    	select, .wid { width: 150px; }
    </style>
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
			<h5>제품 주문서 작성</h5>
			<div class="container" align="center" style="width:310px">
				<form action="/order/custOrder" method="post">
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><strong>주문회사</strong></span>
						</div>
						<select name="cust_cd">
							<option value="1">고객사 1</option>					
							<option value="2">고객사 2</option>
							<option value="3">고객사 3</option>
						</select>
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><strong>생산공장</strong></span>
						</div>
						<select name="plant_cd">
							<option value="1">공장 1</option>					
							<option value="2">공장 2</option>
						</select>
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><strong>주문수량</strong></span>
						</div>
						<input class="wid" type="text" name="order_qty" value="100" required/>
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><strong>납기일자</strong></span>
						</div>
						<input class="wid" type="date" name="delivery_date" required/>
					</div>
					<input type="reset" value="Reset" class="btn btn-outline-success"/>
					<input type="submit" value="Submit" class="btn btn-outline-danger"/>
				</form>
			</div>
		</div>
	</div>	
</body>
</html>
