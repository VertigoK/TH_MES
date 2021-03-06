<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.EquipmentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<EquipmentBean> equipmentList = (ArrayList<EquipmentBean>) request.getAttribute("equipmentList");
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
	<title>Company</title>
	<style>
		.content {
			grid-template-rows: 38px 1fr;
		}
		.line {
			width: 140px;
			height: 49px;
			border-bottom: 1px solid #383636;
			transform: translateY(-23px) translateX(5px) rotate(20deg);
			position: absolute;
			/* z-index: -1; */
		}
		th>div {
			position: relative;
			height: 100%;
			width: 100%;
			top: 0;
			left: 0;
		}
		.bottom {
			position: absolute;
			bottom: 1px;
			left: 5px;
		}
		.top {
			position: absolute;
			top: 1px;
			right: 1px;
		}
		.title {
			display: flex;
			width: 100%;
			height: 33.5px;
			align-items: stretch;
			margin: 0 0 10px 0;
		 }
		.title-left { width: calc(100% - 60px); }
		.title-right {
			width: 60px;
			padding: 5px;
			text-align: right;
			font-wight: blod;
			background-color: #3F5060;
		}
		.title-right a { color: white; }
		.dropdown { margin-bottom: 10px; }
		.dropdown-content a { background-color: white; }
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
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>????????? ?????? ??????</strong></button>
	  			<div class="dropdown-content">
					<a href="/equipment/plant?id=plant_cd&no=1">?????? 1</a>
					<a href="/equipment/plant?id=plant_cd&no=2">?????? 2</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>????????? ?????? ??????</strong></button>
	  			<div class="dropdown-content">
					<a href="/equipment/line?id=line_cd&no=1">?????? 1</a>
					<a href="/equipment/line?id=line_cd&no=2">?????? 2</a>
					<a href="/equipment/line?id=line_cd&no=3">?????? 3</a>
					<a href="/equipment/line?id=line_cd&no=4">?????? 4</a>
					<a href="/equipment/line?id=line_cd&no=5">?????? 5</a>
					<a href="/equipment/line?id=line_cd&no=6">?????? 6</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>????????? ?????? ??????</strong></button>
	  			<div class="dropdown-content">
					<a href="/equipment/process?id=process_cd&no=1">??????</a>
					<a href="/equipment/process?id=process_cd&no=2">?????????</a>
					<a href="/equipment/process?id=process_cd&no=3">??????</a>
					<a href="/equipment/process?id=process_cd&no=4">????????????</a>
					<a href="/equipment/process?id=process_cd&no=5">?????????</a>
				</div>
			</div>
		</div>
		<div class="item">
			<h5>?????? ??????</h5>
			<table class="table-striped">
				<tr>
					<th>????????????</th>
					<th>????????????</th>
					<th>????????????</th>
					<th>??????ID</th>
					<th>????????????</th>
					<th>?????????</th>
					<th>?????????</th>
					<th>????????????</th>
					<th>??????</th>
					<th>??????????????????</th>
					<th>????????????</th>
					<th>????????????(???)</th>
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
					<c:choose>
						<c:when test="${equipment.isUse_yn()}">
							<td>${equipment.isUse_yn()}</td>
							<td>${equipment.getError_cd()}</td>
						</c:when>
						<c:otherwise>
							<td><a href="/equipment/error?equip_id=${equipment.getEquip_id()}&error_cd=${equipment.getError_cd()}" style="color:red"><strong>${equipment.isUse_yn()}</strong></a></td>
							<td><a href="/equipment/error?equip_id=${equipment.getEquip_id()}&error_cd=${equipment.getError_cd()}" style="color:red"><strong>${equipment.getError_cd()}</strong></a></td>
						</c:otherwise>
					</c:choose>
					<td>${equipment.getRun_time()}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>