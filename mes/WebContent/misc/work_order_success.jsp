<%@page import="mes.dto.WorkOrderBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	WorkOrderBean workOrder = (WorkOrderBean) session.getAttribute("workOrderInfo");
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
			<h5>생산지시가 생성되었습니다.</h5>
			<table class="table1 table-striped">
				<tr>
					<th>생산지시번호</th>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>주문번호</th>
					<th>작업시작일</th>
					<th>시작작업조</th>
					<th>작업종료일</th>
					<th>종료작업조</th>
					<th>계획수량</th>
					<th>작업상태</th>
				</tr>
				<tr>
					<td><%=workOrder.getWo_no()%></td>
					<td><%=workOrder.getPlant_cd()%></td>
					<td><%=workOrder.getLine_cd()%></td>
					<td><%=workOrder.getOrder_no()%></td>
					<td><%=workOrder.getStart_date()%></td>
					<td><%=workOrder.getStart_shift()%></td>
					<td><%=workOrder.getEnd_date()%></td>
					<td><%=workOrder.getEnd_shift()%></td>
					<td><%=workOrder.getPlan_qty()%></td>
					<td><%=workOrder.isFlag_end()%></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>