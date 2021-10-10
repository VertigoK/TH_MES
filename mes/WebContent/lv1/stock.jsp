<%@page import="mes.dto.ItemStockBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<Integer> totalStockList = (ArrayList<Integer>) request.getAttribute("totalStockList");	
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
	<title>재고현황</title>
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
		.title-right a { color: white; }
		.dropdown { margin-bottom: 10px; }
		.dropdown-content a { background-color: white; }
	</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
		<jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content" align="center">
		<div class="item">
			<div class="dropdown">
	  			<button>공장별 현황</button>
	  			<div class="dropdown-content">
					<a href="/stock/plant?id=plant_cd&no=1">공장 1</a>
					<a href="/stock/plant?id=plant_cd&no=2">공장 2</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button>품목별 현황</button>
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
					<th>자동차부품_1</th>
					<th>자동차부품_2</th>
					<th>자동차부품_3</th>
					<th>자재_1</th>
					<th>자재_2</th>
					<th>자재_3</th>
				</tr>
				<tr>
					<td>공장1</td>				
					<c:forEach var="totalStock" items="${ totalStockList }" varStatus="status">
						<c:if test="${ status.index < 6 }">
							<td>${ totalStock }</td>
						</c:if>
					</c:forEach>				
				</tr>
				<tr>
					<td>공장2</td>				
					<c:forEach var="totalStock" items="${ totalStockList }" varStatus="status">
						<c:if test="${ status.index >= 6 }">
							<td>${ totalStock }</td>
						</c:if>
					</c:forEach>				
				</tr>
			</table>
		</div>
		<div class="item">
			<div class="title">
				<div class="title-left"><h5>재고 입출고 내역</h5></div>
				<div class="title-right">
						<a href=""><p>더보기</p></a>
				</div>
			</div>
			<table>
				<tr>
					<th>입출고시간</th>
					<th>품목명</th>
					<th>거래처</th>
					<th>입출고유형</th>
					<th>출발창고</th>
					<th>이동창고</th>
					<th>수량</th>
				</tr>
				<c:forEach begin="1" end="10">
				<tr>
					<td>s</td>
					<td>a</td>
					<td>m</td>
					<td>p</td>
					<td>l</td>
					<td>e</td>
					<td>!</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>