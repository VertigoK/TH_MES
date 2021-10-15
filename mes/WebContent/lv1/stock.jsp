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
	  			<button class="btn btn-outline-info"><strong>공장별 재고 현황</strong></button>
	  			<div class="dropdown-content">
					<a href="/stock/plant?id=plant_cd&no=1">공장 1</a>
					<a href="/stock/plant?id=plant_cd&no=2">공장 2</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>품목별 재고 현황</strong></button>
	  			<div class="dropdown-content">
					<a href="/stock/item?id=item_type&no=1">제품</a>
					<a href="/stock/item?id=item_type&no=2">자재</a>
				</div>
			</div>
			<h5>재고 현황</h5>
			<table>
				<tr>
					<th>
						<div>
							<span class="top">재고량</span>
							<span class="bottom">공장명</span>
							<div class="line"></div>
						</div>
					</th>
					<th>제품 1</th>
					<th>제품 2</th>
					<th>제품 3</th>
					<th>자재 1</th>
					<th>자재 2</th>
					<th>자재 3</th>
				</tr>
				<tr>
					<td>공장 1</td>				
					<c:forEach var="itemStockQty" items="${ itemStockQtys }" varStatus="status">
						<c:if test="${ status.index < 6 }">
							<td>${ itemStockQty }</td>
						</c:if>
					</c:forEach>				
				</tr>
				<tr>
					<td>공장 2</td>				
					<c:forEach var="itemStockQty" items="${ itemStockQtys }" varStatus="status">
						<c:if test="${ status.index >= 6 }">
							<td>${ itemStockQty }</td>
						</c:if>
					</c:forEach>				
				</tr>
			</table>
		</div>
		<div class="item">
			<h5>품목 입출고 내역</h5>
			<table>
				<tr>
					<th>입출고코드</th>
					<th>공장</th>
					<th>품목코드</th>
					<th>품목타입</th>
					<th>입출고시간</th>
					<th>입출고유형</th>
					<th>출발창고명</th>
					<th>이동창고명</th>
					<th>거래처</th>
					<th>이동수량</th>
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