<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.EquipmentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<EquipmentBean> equipmentList = (ArrayList<EquipmentBean>) request.getAttribute("equipmentList");
	MemberBean member = (MemberBean) session.getAttribute("logInInfo");
	String no = request.getParameter("no");
	String name = null;
	switch(no) {
		case "1": name = "커팅"; break;
		case "2": name = "드릴링"; break;
		case "3": name = "조립"; break;
		case "4": name = "치수검사"; break;
		case "5": name = "홀검사"; break;
	}
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
	<title>Company</title>
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
			<h5>공정별 설비 현황</h5>
			<table class="table1 table-striped">
				<tr>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>공정코드</th>
					<th>설비ID</th>
					<th>설비코드</th>
					<th>설비명</th>
					<th>모델명</th>
					<th>점검주기</th>
					<th>타입</th>
					<th>사용가능여부</th>
					<th>에러코드</th>
					<th>가동시간</th>
				</tr>
				<c:forEach var="equipment" items="${equipmentList}">
				<tr>
					<td>${equipment.getPlant_cd()}</td>
					<td>${equipment.getLine_cd()}</td>
					<td>${equipment.getProcess_cd()}</td>
					<td>${equipment.getEquip_id()}</td>
					<td>${equipment.getEquip_cd()}</td>
					<td>${equipment.getEquip_nm()}</td>
					<td>${equipment.getEquip_model()}</td>
					<td>${equipment.getCheck_term()}</td>
					<td>${equipment.getUse_type()}</td>
					<td>${equipment.isUse_yn()}</td>
					<td>${equipment.getError_cd()}</td>
					<td>${equipment.getRun_time()}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>