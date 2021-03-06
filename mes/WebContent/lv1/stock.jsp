<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.ItemInOutBean"%>
<%@page import="mes.dto.ItemStockBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int[] itemStockQtys = (int[]) request.getAttribute("itemStockQtys");
	@SuppressWarnings("unchecked")
	ArrayList<ItemInOutBean> itemInOutList = (ArrayList<ItemInOutBean>) request.getAttribute("itemInOutList");
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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<link rel="stylesheet" href="/css/lv1StyleSheet.css" />
	<title>Company</title>
	<style>
		.line {
			width: 140px;
			height: 49px;
			border-bottom: 1px solid #383636;
			transform: translateY(-23px) translateX(5px) rotate(20deg);
			position: absolute;
			/* z-index: -1; */
		}
		th>div {
			position: relative;
			height: 100%;
			width: 100%;
			top: 0;
			left: 0;
		}
		.bottom {
			position: absolute;
			bottom: 1px;
			left: 5px;
		}
		
		.top {
			position: absolute;
			top: 1px;
			right: 1px;
		}
		.title {
			display: flex;
			width: 100%;
			height: 33.5px;
			align-items: stretch;
			margin: 0 0 10px 0;
		 }
		.title-left { width: calc(100% - 60px); }
		.title-right {
			width: 60px;
			padding: 5px;
			text-align: right;
			font-wight: blod;
			background-color: #3F5060;
		}
		.inner-notice {
			display: flex;
			width: 100%;
			height: 33px;
			align-items: stretch;
			margin: 0 0 10px 0;
			overflow: hidden;
		}
		.inner-left { width: calc(100% - 60px); }
		.inner-right { 
			text-align: right;
			width: 60px;
			padding: 5px;
			background-color: #3F5060;
		}
		.inner-right a { color: white; }
		.flex { display: inline-flex; }
		.flex a p { display: inline-block; }
		.nullMain {
			grid-column: 1 / span 12;
			grid-row: 1 / span 12;
			display: flex;
			justify-content: center;
			align-items: center;
			overflow: hidden;
		}
		.title-right a { color: white; }
		.dropdown { margin-bottom: 10px; }
		.dropdown-content a { background-color: white; }
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
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>????????? ?????? ??????</strong></button>
	  			<div class="dropdown-content">
					<a href="/stock/plant?id=plant_cd&no=1">?????? 1</a>
					<a href="/stock/plant?id=plant_cd&no=2">?????? 2</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>????????? ?????? ??????</strong></button>
	  			<div class="dropdown-content">
					<a href="/stock/item?id=item_type&no=1">??????</a>
					<a href="/stock/item?id=item_type&no=2">??????</a>
				</div>
			</div>
			<h5>?????? ??????</h5>
			<table>
				<tr>
					<th>
						<div>
							<span class="top">?????????</span>
							<span class="bottom">?????????</span>
							<div class="line"></div>
						</div>
					</th>
					<th>?????? 1</th>
					<th>?????? 2</th>
					<th>?????? 3</th>
					<th>?????? 1</th>
					<th>?????? 2</th>
					<th>?????? 3</th>
				</tr>
				<tr>
					<td>?????? 1</td>				
					<c:forEach var="itemStockQty" items="${ itemStockQtys }" varStatus="status">
						<c:if test="${ status.index < 6 }">
							<td>${ itemStockQty }</td>
						</c:if>
					</c:forEach>				
				</tr>
				<tr>
					<td>?????? 2</td>				
					<c:forEach var="itemStockQty" items="${ itemStockQtys }" varStatus="status">
						<c:if test="${ status.index >= 6 }">
							<td>${ itemStockQty }</td>
						</c:if>
					</c:forEach>				
				</tr>
			</table>
		</div>
		<div class="item">
			<h5>?????? ????????? ??????</h5>
			<table>
				<tr>
					<th>???????????????</th>
					<th>??????</th>
					<th>????????????</th>
					<th>????????????</th>
					<th>???????????????</th>
					<th>???????????????</th>
					<th>???????????????</th>
					<th>???????????????</th>
					<th>?????????</th>
					<th>????????????</th>
				</tr>
				<c:forEach var="itemInOut" items="${itemInOutList}">
				<tr>
					<td>${itemInOut.getInout_cd()}</td>
					<td>${itemInOut.getPlant_cd()}</td>
					<td>${itemInOut.getItem_cd()}</td>
					<td>${itemInOut.getItem_type()}</td>
					<td>${itemInOut.getInout_dt()}</td>
					<td>${itemInOut.getInout_type()}</td>
					<td>${itemInOut.getStorage_from_nm()}</td>
					<td>${itemInOut.getStorage_to_nm()}</td>
					<c:choose>
						<c:when test="${itemInOut.getCust_cd() != 0}">
							<td>${itemInOut.getCust_cd()}</td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<td>${itemInOut.getItem_cnt()}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>