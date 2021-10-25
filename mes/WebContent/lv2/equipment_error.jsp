<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.ErrorLogBean"%>
<%@page import="mes.dto.ErrorBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ErrorBean equipmentErrorDetail = (ErrorBean) request.getAttribute("equipmentErrorDetail");
	@SuppressWarnings("unchecked")
	ArrayList<ErrorLogBean> equipmentErrorLog = (ArrayList<ErrorLogBean>) request.getAttribute("equipmentErrorLog");
	MemberBean member = (MemberBean) session.getAttribute("logInInfo");
	int equip_id = Integer.parseInt(request.getParameter("equip_id"));
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
	<title>Company</title>
	<style>
		.content {
			grid-template-rows: 38px 1fr 1fr;
		}
	</style>
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
			<a href="#" class="btn btn-outline-danger"><strong>에러 해제</strong></a>
		</div>
		<div class="item">
			<h5><%=equip_id%>번 설비 에러 내용</h5>
			<table class="table-striped">
				<tr>
					<th>에러코드</th>
					<th>에러내용</th>
					<th>에러등급</th>
					<th>가동중단시간(분)</th>
					<th>수정일</th>
				</tr>
				<tr>
					<td><%=equipmentErrorDetail.getError_cd()%></td>
					<td><%=equipmentErrorDetail.getError_msg()%></td>
					<td><%=equipmentErrorDetail.getError_gd()%></td>
					<td><%=equipmentErrorDetail.getDown_dr()%></td>
					<td><%=equipmentErrorDetail.getUpdate_dt()%></td>
				</tr>
			</table>
		</div>
		<div class="item">
			<h5><%=equip_id%>번 설비 에러 발생 이력</h5>
			<table class="table-striped">
				<tr>
					<th>로그번호</th>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>공정코드</th>
					<th>설비ID</th>
					<th>에러코드</th>
					<th>에러등급</th>
					<th>발생시간</th>
					<th>해졔시간</th>
				</tr>
				<c:forEach var="equipmentError" items="${equipmentErrorLog}">
				<tr>
					<td>${equipmentError.getLog_no()}</td>
					<td>${equipmentError.getPlant_cd()}</td>
					<td>${equipmentError.getLine_cd()}</td>
					<td>${equipmentError.getProcess_cd()}</td>
					<td>${equipmentError.getEquip_id()}</td>
					<td>${equipmentError.getError_cd()}</td>
					<td>${equipmentError.getError_gd()}</td>
					<td>${equipmentError.getStart_dt()}</td>
					<td>${equipmentError.getEnd_dt()}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>			
</body>
</html>