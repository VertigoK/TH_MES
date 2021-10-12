<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.ProductionHistoryBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ProductionHistoryBean productionHistory = (ProductionHistoryBean) session.getAttribute("productionHistoryInfo");
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
			<h5>신규 생성된 생산이력</h5>
			<table class="table1 table-striped">
				<tr>
					<th>생산지시번호</th>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>품목코드</th>
					<th>생산지시SEQ</th>
					<th>작업시작시간</th>
					<th>작업종료시간</th>
					<th>투입수량</th>
					<th>배출수량</th>
					<th>NG수량</th>
				</tr>
				<tr>
					<td><%=productionHistory.getWo_no()%></td>
					<td><%=productionHistory.getPlant_cd()%></td>
					<td><%=productionHistory.getLine_cd()%></td>
					<td><%=productionHistory.getItem_cd()%></td>
					<td><%=productionHistory.getWo_seq()%></td>
					<td><%=productionHistory.getStart_dt()%></td>
					<td><%=productionHistory.getEnd_dt()%></td>
					<td><%=productionHistory.getIn_qty()%></td>
					<td><%=productionHistory.getOut_qty()%></td>
					<td><%=productionHistory.getNg_qty()%></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>