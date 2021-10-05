<%@page import="mes.dto.WorkOrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<WorkOrderBean> workOrderList = (ArrayList<WorkOrderBean>) request.getAttribute("workOrderList");
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
		<h5>생산지시 현황</h5>
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
				<th>생산시작</th>
			</tr>
			<c:forEach var="workOrderList" items="${workOrderList}">
			<tr>
				<td>${workOrderList.getWo_no()}</td>
				<td>${workOrderList.getPlant_cd()}</td>
				<td>${workOrderList.getLine_cd()}</td>
				<td>${workOrderList.getOrder_no()}</td>
				<td>${workOrderList.getStart_date()}</td>
				<td>${workOrderList.getStart_shift()}</td>
				<td>${workOrderList.getEnd_date()}</td>
				<td>${workOrderList.getEnd_shift()}</td>
				<td>${workOrderList.getPlan_qty()}</td>
				<td>${workOrderList.isFlag_end()}</td>
				<td><a href="/production/start?wo_no=${workOrderList.getWo_no()}" class="btn btn-danger">생산</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>