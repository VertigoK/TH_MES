<%@page import="java.sql.Date"%>
<%@page import="mes.dto.CustomerOrderBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");

	int plant_cd = custOrder.getPlant_cd();
	int item_cd = custOrder.getItem_cd();
	int order_no = custOrder.getOrder_no();
	int cust_cd = custOrder.getCust_cd();
	int order_qty = custOrder.getOrder_qty();
	Date order_date = custOrder.getOrder_date();
	Date delivery_date = custOrder.getDelivery_date();
	Date finished_date = custOrder.getFinished_date();
	boolean order_status = custOrder.isOrder_status();
	int delayed_date = custOrder.getDelayed_date();
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
	<title>고객사 제품 발주</title>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
		<jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content" align="center">
		<h5>고객사에서 제품을 발주했습니다.</h5>
		<table class="table1 table-striped">
			<tr>
				<th>주문번호</th>
				<th>발주처</th>
				<th>생산공장</th>
				<th>생산제품</th>
				<th>주문수량</th>
				<th>주문일</th>
				<th>납기일</th>
				<th>마감일</th>
				<th>주문상태</th>
				<th>납기지연일</th>
			</tr>
			<tr>
				<td><%=order_no%></td>
				<td><%=cust_cd%></td>
				<td><%=plant_cd%></td>
				<td><%=item_cd%></td>
				<td><%=order_qty%></td>
				<td><%=order_date%></td>
				<td><%=delivery_date%></td>
				<td><%=finished_date%></td>
				<td><%=order_status%></td>
				<td><%=delayed_date%></td>
			</tr>
		</table>
	</div>
</body>
</html>