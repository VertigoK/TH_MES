<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.WorkerBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<WorkerBean> workerList = (ArrayList<WorkerBean>) request.getAttribute("workerList");
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
	<title>HR현황</title>
	<style>
		.dropdown-content a { background-color: white; }
	</style>
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
			<div class="dropdown">
	  			<button>공장별 근무자</button>
	  			<div class="dropdown-content">
					<a href="/hr/plant?id=plant_cd&no=1">공장 1</a>
					<a href="/hr/plant?id=plant_cd&no=2">공장 2</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button>라인별 근무자</button>
	  			<div class="dropdown-content">
					<a href="/hr/line?id=line_cd&no=1">라인 1</a>
					<a href="/hr/line?id=line_cd&no=2">라인 2</a>
					<a href="/hr/line?id=line_cd&no=3">라인 3</a>
					<a href="/hr/line?id=line_cd&no=4">라인 4</a>
					<a href="/hr/line?id=line_cd&no=5">라인 5</a>
					<a href="/hr/line?id=line_cd&no=6">라인 6</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button>공정별 근무자</button>
	  			<div class="dropdown-content">
					<a href="/hr/loc?id=worker_loc&no=1">생산</a>
					<a href="/hr/loc?id=worker_loc&no=2">품질검사</a>
				</div>
			</div>
			<h5>HR현황</h5>
		</div>
		<div class="item">
			<table>
				<tr>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>근무위치</th>
					<th>근무시간</th>
					<th>근무자명</th>
				</tr>
				<c:forEach var="worker" items="${ workerList }">
					<tr>
						<td>${ worker.getPlant_cd() }</td>
						<td>${ worker.getLine_cd() }</td>
						<td>${ worker.getWorker_loc() }</td>
						<td>${ worker.getWorker_time() }</td>
						<td>${ worker.getWorker_nm() }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>		
</body>
</html>