<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.ProductionHistoryBean"%>
<%@page import="mes.dto.WorkOrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<WorkOrderBean> workOrderList = (ArrayList<WorkOrderBean>) request.getAttribute("workOrderList");
	@SuppressWarnings("unchecked")	
	ArrayList<ProductionHistoryBean> productionHistoryList = (ArrayList<ProductionHistoryBean>) request.getAttribute("productionHistoryList");
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
    <title>Telstar-Hommel</title>
	<style>
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
	  			<button class="btn btn-outline-info"><strong>공장별 생산 데이터</strong></button>
	  			<div class="dropdown-content">
					<a href="/production/plant?id=plant_cd&no=1">공장 1</a>
					<a href="/production/plant?id=plant_cd&no=2">공장 2</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>라인별 생산 데이터</strong></button>
	  			<div class="dropdown-content">
					<a href="/production/line?id=line_cd&no=1">라인 1</a>
					<a href="/production/line?id=line_cd&no=2">라인 2</a>
					<a href="/production/line?id=line_cd&no=3">라인 3</a>
					<a href="/production/line?id=line_cd&no=4">라인 4</a>
					<a href="/production/line?id=line_cd&no=5">라인 5</a>
					<a href="/production/line?id=line_cd&no=6">라인 6</a>
				</div>
			</div>
			<h5>생산지시 현황</h5>
			<table class="table1 table-striped">
				<tr>
					<th>생산지시번호</th>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>주문번호</th>
					<th>품목코드</th>
					<th>작업시작일</th>
					<th>시작작업조</th>
					<th>작업종료일</th>
					<th>종료작업조</th>
					<th>계획수량</th>
					<th>작업상태</th>
					<th>생산시작</th>
				</tr>
				<c:forEach var="workOrder" items="${workOrderList}">
				<tr>
					<td>${workOrder.getWo_no()}</td>
					<td>${workOrder.getPlant_cd()}</td>
					<td>${workOrder.getLine_cd()}</td>
					<td>${workOrder.getOrder_no()}</td>
					<td>${workOrder.getItem_cd()}</td>
					<td>${workOrder.getStart_date()}</td>
					<td>${workOrder.getStart_shift()}</td>
					<td>${workOrder.getEnd_date()}</td>
					<td>${workOrder.getEnd_shift()}</td>
					<td>${workOrder.getPlan_qty()}</td>
					<td>${workOrder.isFlag_end()}</td>
					<c:choose>
						<c:when test="${workOrder.isFlag_end() == false}">
							<td><a href="/production/start?wo_no=${workOrder.getWo_no()}" class="btn btn-danger">생산</a></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:forEach>
			</table>			
		</div>
		<div class="item">
			<h5>생산 이력</h5>
			<table class="table1 table-striped">
				<tr>
					<th>생산지시번호</th>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>품목코드</th>
					<th>작업시작시간</th>
					<th>작업종료시간</th>
					<th>투입수량</th>
					<th>배출수량</th>
					<th>NG수량</th>
				</tr>
				<c:forEach var="productionHistory" items="${productionHistoryList}">
				<tr>
					<td>${productionHistory.getWo_no()}</td>
					<td>${productionHistory.getPlant_cd()}</td>
					<td>${productionHistory.getLine_cd()}</td>
					<td>${productionHistory.getItem_cd()}</td>
					<td>${productionHistory.getStart_dt()}</td>
					<td>${productionHistory.getEnd_dt()}</td>
					<td>${productionHistory.getIn_qty()}</td>
					<td>${productionHistory.getOut_qty()}</td>
					<td>${productionHistory.getNg_qty()}</td>
				</tr>
				</c:forEach>
			</table>			
		</div>		
	</div>
</body>
</html>