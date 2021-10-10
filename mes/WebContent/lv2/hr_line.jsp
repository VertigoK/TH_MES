<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.WorkerBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<WorkerBean> hrLineList = (ArrayList<WorkerBean>) request.getAttribute("hrLineList");
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
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
		<jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content">
		<div class="item">
			<table>
				<tr>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>근무위치</th>
					<th>근무시간</th>
					<th>근무자명</th>
				</tr>
				<c:forEach var="line" items="${ hrLineList }">
					<tr>
						<td>${ line.getPlant_cd() }</td>
						<td>${ line.getLine_cd() }</td>
						<td>${ line.getWorker_loc() }</td>
						<td>${ line.getWorker_time() }</td>
						<td>${ line.getWorker_nm() }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>