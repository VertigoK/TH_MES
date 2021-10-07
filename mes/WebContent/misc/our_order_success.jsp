<%@page import="mes.dto.OurOrderBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	OurOrderBean ourOrder = (OurOrderBean) session.getAttribute("ourOrderInfo");
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
	<title>Telstar-Hommel</title>
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
			<h5>거래처에 자재를 발주했습니다.</h5>
			<table class="table1 table-striped">
				<tr>
					<th>주문번호</th>
					<th>공급회사</th>
					<th>납품공장</th>
					<th>납품자재</th>
					<th>주문수량</th>
					<th>주문일</th>
					<th>납품상태</th>
				</tr>
				<tr>
					<td><%=ourOrder.getOrder_no()%></td>
					<td><%=ourOrder.getCust_cd()%></td>
					<td><%=ourOrder.getPlant_cd()%></td>
					<td><%=ourOrder.getItem_cd()%></td>
					<td><%=ourOrder.getOrder_qty()%></td>
					<td><%=ourOrder.getOrder_dt()%></td>
					<td><%=ourOrder.isOrder_status()%></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>