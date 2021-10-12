<%@page import="mes.dto.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mes.dto.ItemStockBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<ItemStockBean> plantStockList = (ArrayList<ItemStockBean>) request.getAttribute("plantStockList");
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
	<title>공장별 재고</title>
	<style>
		.span {
			width: 140px;
		}
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
	<div class="content">
		<div class="item title">
			<c:if test="${ param.no == 1 }">
				<h5>공장1 재고</h5>
			</c:if>
			<c:if test="${ param.no == 2 }">
				<h5>공장2 재고</h5>
			</c:if>
		</div>
		<!-- 자재창고 -->
		<div class="item">
			<table>
				<tr>
					<th rowspan="2" class="span">자재창고</th>
					<th>자재1</th>
					<th>자재2</th>
					<th>자재3</th>
				</tr>
				<tr>
					<c:forEach var="out" items="${ plantStockList }">
						<c:if test="${ out.getStorage_cd() == 1 or out.getStorage_cd() == 9 }">
							<td>${ out.getItem_qty() }</td>
						</c:if>
					</c:forEach>
				</tr>
			</table>
		</div>
		
		<!-- 라인창고1,4 -->
		<div class="item">
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">라인1</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">라인4</th>
					</c:if>
					<th>자재1</th>
					<th>자재2</th>
					<th>자재3</th>
					<th>자동차부품_1</th>
					<th>자동차부품_2</th>
					<th>자동차부품_3</th>
				</tr>
				<tr>
					<c:forEach var="in" items="${ plantStockList }">
						<c:if test="${ in.getStorage_cd() == 3 or in.getStorage_cd() == 4 or in.getStorage_cd() == 11 or in.getStorage_cd() == 12 }">
							<td>${ in.getItem_qty() }</td>
						</c:if>
					</c:forEach>
				</tr>
			</table>
		</div>
		
		<!-- 라인창고2,5 -->
		<div class="item">
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">라인2</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">라인5</th>
					</c:if>
					<th>자재1</th>
					<th>자재2</th>
					<th>자재3</th>
					<th>자동차부품_1</th>
					<th>자동차부품_2</th>
					<th>자동차부품_3</th>
				</tr>
				<tr>
					<c:forEach var="in" items="${ plantStockList }">
						<c:if test="${ in.getStorage_cd() == 5 or in.getStorage_cd() == 6 or in.getStorage_cd() == 13 or in.getStorage_cd() == 14 }">
							<td>${ in.getItem_qty() }</td>
						</c:if>
					</c:forEach>
				</tr>
			</table>
		</div>
		<!-- 라인창고3,6 -->
		<div class="item">
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">라인3</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">라인6</th>
					</c:if>
					<th>자재1</th>
					<th>자재2</th>
					<th>자재3</th>
					<th>자동차부품_1</th>
					<th>자동차부품_2</th>
					<th>자동차부품_3</th>
				</tr>
				<tr>
					<c:forEach var="in" items="${ plantStockList }">
						<c:if test="${ in.getStorage_cd() == 7 or in.getStorage_cd() == 8 or in.getStorage_cd() == 15 or in.getStorage_cd() == 16 }">
							<td>${ in.getItem_qty() }</td>
						</c:if>
					</c:forEach>
				</tr>
			</table>
		</div>
		
		<!-- 제품창고 -->
		<div class="item">
			<table>
				<tr>
					<th rowspan="2" class="span">제품창고</th>
					<th>자동차부품_1</th>
					<th>자동차부품_2</th>
					<th>자동차부품_3</th>
				</tr>
				<tr>
					<c:forEach var="out" items="${ plantStockList }">
						<c:if test="${ out.getStorage_cd() == 2 or out.getStorage_cd() == 10 }">
							<td>${ out.getItem_qty() }</td>
						</c:if>
					</c:forEach>
				</tr>
			</table>
		</div>	
	</div>
</body>
</html>