<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mes.dto.OurOrderBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
ArrayList<OurOrderBean> orderInList = (ArrayList<OurOrderBean>) request.getAttribute("orderOutList");
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
		<h5>자사 자재 발주 현황</h5>
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
			<c:forEach var="orderOut" items="${orderOutList}">
			<tr>
				<td>${orderOut.getOrder_no()}</td>
				<td>${orderOut.getCust_cd()}</td>
				<td>${orderOut.getPlant_cd()}</td>
				<td>${orderOut.getItem_cd()}</td>
				<td>${orderOut.getOrder_qty()}</td>
				<td>${orderOut.getOrder_dt()}</td>
				<td>${orderOut.isOrder_status()}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>