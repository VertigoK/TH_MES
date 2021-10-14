<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.ItemStockBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<ItemStockBean> plantStockList = (ArrayList<ItemStockBean>) request.getAttribute("itemTypeStockList");
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
	<title>Company</title>
	<style>
		table { margin: auto; }
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
			<c:if test="${param.no == 1 }">
				<h5>제품 재고</h5>
			</c:if>			
			<c:if test="${param.no == 2 }">
				<h5>자재 재고</h5>
			</c:if>			
		</div>
		<!-- 공장1 품목현황 -->
		<div class="item" style="text-align: center;">
			<h5 style="display: inline-block;">공장1</h5>
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">자동차부품_1</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">자재_1</th>
					</c:if>
					<c:if test="${ param.no == 1 }">
						<th>제품창고</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th>자재창고</th>
					</c:if>
					<th>라인1</th>
					<th>라인2</th>
					<th>라인3</th>
				</tr>
					<tr>
						<c:forEach var="storage" items="${ itemTypeStockList }">
							<c:if test="${ storage.getStorage_cd() <= 8 and (storage.getItem_cd() == 1 or storage.getItem_cd() == 4) }">
								<td>${ storage.getItem_qty() }</td>
							</c:if>
						</c:forEach>
					</tr>
			</table>
			<br />
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">자동차부품_2</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">자재_2</th>
					</c:if>
					<c:if test="${ param.no == 1 }">
						<th>제품창고</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th>자재창고</th>
					</c:if>
					<th>라인1</th>
					<th>라인2</th>
					<th>라인3</th>
				</tr>
					<tr>
						<c:forEach var="storage" items="${ itemTypeStockList }">
							<c:if test="${ storage.getStorage_cd() <= 8 and (storage.getItem_cd() == 2 or storage.getItem_cd() == 5) }">
								<td>${ storage.getItem_qty() }</td>
							</c:if>
						</c:forEach>
					</tr>
			</table>
			<br />
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">자동차부품_3</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">자재_3</th>
					</c:if>
					<c:if test="${ param.no == 1 }">
						<th>제품창고</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th>자재창고</th>
					</c:if>
					<th>라인1</th>
					<th>라인2</th>
					<th>라인3</th>
				</tr>
					<tr>
						<c:forEach var="storage" items="${ itemTypeStockList }">
							<c:if test="${ storage.getStorage_cd() <= 8 and (storage.getItem_cd() == 3 or storage.getItem_cd() == 6) }">
								<td>${ storage.getItem_qty() }</td>
							</c:if>
						</c:forEach>
					</tr>
			</table>
		</div>
		<!-- 공장2 품목현황 -->
		<div class="item" style="text-align: center;">
			<h5 style="display: inline-block;">공장2</h5>
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">자동차부품_1</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">자재_1</th>
					</c:if>
					<c:if test="${ param.no == 1 }">
						<th>제품창고</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th>자재창고</th>
					</c:if>
					<th>라인4</th>
					<th>라인5</th>
					<th>라인6</th>
				</tr>
					<tr>
						<c:forEach var="storage" items="${ itemTypeStockList }">
							<c:if test="${ storage.getStorage_cd() > 8 and (storage.getItem_cd() == 1 or storage.getItem_cd() == 4) }">
								<td>${ storage.getItem_qty() }</td>
							</c:if>
						</c:forEach>
					</tr>
			</table>
			<br />
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">자동차부품_2</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">자재_2</th>
					</c:if>
					<c:if test="${ param.no == 1 }">
						<th>제품창고</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th>자재창고</th>
					</c:if>
					<th>라인1</th>
					<th>라인2</th>
					<th>라인3</th>
				</tr>
					<tr>
						<c:forEach var="storage" items="${ itemTypeStockList }">
							<c:if test="${ storage.getStorage_cd() > 8 and (storage.getItem_cd() == 2 or storage.getItem_cd() == 5) }">
								<td>${ storage.getItem_qty() }</td>
							</c:if>
						</c:forEach>
					</tr>
			</table>
			<br />
			<table>
				<tr>
					<c:if test="${ param.no == 1 }">
						<th rowspan="2" class="span">자동차부품_3</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th rowspan="2" class="span">자재_3</th>
					</c:if>
					<c:if test="${ param.no == 1 }">
						<th>제품창고</th>
					</c:if>
					<c:if test="${ param.no == 2 }">
						<th>자재창고</th>
					</c:if>
					<th>라인1</th>
					<th>라인2</th>
					<th>라인3</th>
				</tr>
					<tr>
						<c:forEach var="storage" items="${ itemTypeStockList }">
							<c:if test="${ storage.getStorage_cd() > 8 and (storage.getItem_cd() == 3 or storage.getItem_cd() == 6) }">
								<td>${ storage.getItem_qty() }</td>
							</c:if>
						</c:forEach>
					</tr>
			</table>
		</div>
	</div>
</body>
</html>