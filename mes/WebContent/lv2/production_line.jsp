<%@page import="mes.dto.ProductionBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<ProductionBean> productiontList = (ArrayList<ProductionBean>) request.getAttribute("productionList");
	String no = request.getParameter("no");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta charset="UTF-8">
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
		<table class="table1 table-striped">
			<tr>
				<th>제품일련번호</th>
				<th>생산지시번호</th>
				<th>공장코드</th>
				<th>라인코드</th>
				<th>픔목코드</th>
				<th>근무자번호</th>
				<th>가로길이</th>
				<th>세로길이</th>
				<th>가로면 세로편차</th>
				<th>세로면 가로편차</th>
				<th>홀 가로길이</th>
				<th>홀 세로길이</th>
				<th>홀 가로중심</th>
				<th>홀 세로중심</th>
				<th>가로 직진도</th>
				<th>세로 직진도</th>
				<th>홀 직경</th>
				<th>홀 비율</th>
				<th>생산시간</th>
			</tr>
			<c:forEach var="production" items="${productionList}">
			<tr>
				<td>${production.getSerial_no()}</td>
				<td>${production.getWo_no()}</td>
				<td>${production.getPlant_cd()}</td>
				<td>${production.getLine_cd()}</td>
				<td>${production.getItem_cd()}</td>
				<td>${production.getWorker_no()}</td>
				<td>${production.getDim_x()}</td>
				<td>${production.getDim_y()}</td>
				<td>${production.getDim_h()}</td>
				<td>${production.getDim_w()}</td>
				<td>${production.getHole_x()}</td>
				<td>${production.getHole_y()}</td>
				<td>${production.getHole_xc()}</td>
				<td>${production.getHole_yc()}</td>
				<td>${production.getStr_x()}</td>
				<td>${production.getStr_y()}</td>
				<td>${production.getHole_d()}</td>
				<td>${production.getHole_ratio()}</td>
				<td>${production.getPrd_dt()}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>