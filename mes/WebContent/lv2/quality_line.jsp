<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.QualityBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<QualityBean> qualitytList = (ArrayList<QualityBean>) request.getAttribute("qualityList");
	String no = request.getParameter("no");
	MemberBean member = (MemberBean) session.getAttribute("logInInfo");
%>
<!DOCTYPE html>
<html>
<head>
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
	<title>Company</title>
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
			<h5>라인별 품질 데이터</h5>
			<table class="table1 table-striped">
				<tr>
					<th>공장 코드</th>
					<th>라인 코드</th>
					<th>픔목 코드</th>
					<th>근무자 번호</th>
					<th>제품일련번호</th>
					<th>가로길이 검사</th>
					<th>세로길이 검사</th>
					<th>홀 가로중심 검사</th>
					<th>홀 세로중심 검사</th>
					<th>가로 직진도 검사</th>
					<th>세로 직진도 검사</th>
					<th>홀 직경 검사</th>
					<th>홀 비율 검사</th>
					<th>검사종합결과</th>			
				</tr>
				<c:forEach var="quality" items="${qualityList}">
				<tr>
					<td>${quality.getPlant_cd()}</td>
					<td>${quality.getLine_cd()}</td>
					<td>${quality.getItem_cd()}</td>
					<td>${quality.getWorker_no()}</td>
					<td>${quality.getSerial_no()}</td>
					<td>${quality.isDimcheck_x()}</td>
					<td>${quality.isDimcheck_y()}</td>
					<td>${quality.isHolecheck_xc()}</td>
					<td>${quality.isHolecheck_yc()}</td>
					<td>${quality.isDimcheck_hx()}</td>
					<td>${quality.isDimcheck_wy()}</td>
					<td>${quality.isHolecheck_d()}</td>
					<td>${quality.isHolecheck_ratio()}</td>
					<td>${quality.isCheck_result()}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>