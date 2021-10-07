<%@page import="mes.dto.CustomerOrderBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");
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
	<style>
		.table1 {
			font-family: Helvectica;
			font-size: 10;
			width: 100%;
			text-align: center;
		}
	</style>
	<title>고객사 제품 주문 성공</title>
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
			<h5>고객사에서 제품을 주문했습니다.</h5>
			<table class="table1 table-striped">
				<tr>
					<th>주문번호</th>
					<th>주문회사</th>
					<th>생산공장</th>
					<th>생산제품</th>
					<th>주문수량</th>
					<th>주문일</th>
					<th>납기일</th>
					<th>마감일</th>
					<th>납품상태</th>
					<th>납기지연일</th>
				</tr>
				<tr>
					<td><%=custOrder.getOrder_no()%></td>
					<td><%=custOrder.getCust_cd()%></td>
					<td><%=custOrder.getPlant_cd()%></td>
					<td><%=custOrder.getItem_cd()%></td>
					<td><%=custOrder.getOrder_qty()%></td>
					<td><%=custOrder.getOrder_date()%></td>
					<td><%=custOrder.getDelivery_date()%></td>
					<td><%=custOrder.getFinished_date()%></td>
					<td><%=custOrder.isOrder_status()%></td>
					<td><%=custOrder.getDelayed_date()%></td>
				</tr>
			</table>
			<br /><br />
			<a href="/order/inList" class="btn btn-info">제품 주문 현황</a>
		</div>
	</div>
</body>
</html>