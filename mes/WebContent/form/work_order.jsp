<%@page import="mes.dto.CustomerOrderBean"%>
<%@page import="mes.dto.WorkOrderBean"%>
<%@page import="mes.dto.LineBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");
	@SuppressWarnings("unchecked")
	ArrayList<Integer> requiredQtys = (ArrayList<Integer>) session.getAttribute("requiredQtysInfo");
	@SuppressWarnings("unchecked")
	ArrayList<LineBean> lineList = (ArrayList<LineBean>) request.getAttribute("lineList");
	@SuppressWarnings("unchecked")
	ArrayList<WorkOrderBean> workOrderList = (ArrayList<WorkOrderBean>) request.getAttribute("workOrderList");
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
		form {
			width: 310px;
		}
		.input-group-prepend { width: 100px; }
		.wid {width: 150px; }
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
		<div class="item">
			<h5>제품 주문 정보</h5>
			<table class="table1 table-striped">
				<tr>
					<th>주문번호</th>
					<th>주문회사</th>
					<th>생산공장</th>
					<th>주문제품</th>
					<th>주문수량</th>
					<th style="color:red">계획수량</th>
					<th>주문일</th>
					<th style="color:red">납기일</th>
					<th>마감일</th>
					<th>납품상태</th>
					<th>납기지연일</th>
				</tr>
				<tr>
					<td><%=custOrder.getOrder_no()%></td>
					<td><%=custOrder.getItem_cd()%></td>
					<td><%=custOrder.getPlant_cd()%></td>
					<td><%=custOrder.getItem_cd()%></td>
					<td><%=custOrder.getOrder_qty()%></td>
					<td style="color:red"><%=requiredQtys.get(0)%></td>
					<td><%=custOrder.getOrder_date()%></td>
					<td style="color:red"><%=custOrder.getDelivery_date()%></td>
					<td><%=custOrder.getFinished_date()%></td>
					<td><%=custOrder.isOrder_status()%></td>
					<td><%=custOrder.getDelayed_days()%></td>
				</tr>
			</table>
			<br />
			<h5>[공장 <%=custOrder.getPlant_cd()%>] 라인 현황</h5>
			<table class="table1 table-striped">
				<tr>
					<th>라인코드</th>
					<th>공장코드</th>
					<th>사용가능여부</th>
					<th>비고</th>
				</tr>
				<c:forEach var="line" items="${lineList}">
				<tr>
					<td>${line.getLine_cd()}</td>
					<td>${line.getPlant_cd()}</td>
					<td>${line.isUse_yn()}</td>
					<td>${line.getRemark()}</td>
				</tr>
				</c:forEach>
			</table>
			<br />
			<h5>[공장 <%=custOrder.getPlant_cd()%>] 생산지시 현황</h5>
			<table class="table1 table-striped">
				<tr>
					<th>주문번호</th>
					<th>생산지시번호</th>
					<th>공장코드</th>
					<th>라인코드</th>
					<th>작업시작일</th>
					<th>시작작업조</th>
					<th>작업종료일</th>
					<th>종료작업조</th>
					<th>계획수량</th>
					<th>작업상태</th>
				</tr>
				<c:forEach var="workOrderList" items="${workOrderList}">
					<c:if test="${workOrderList.isFlag_end() == false}">
						<tr>
							<td>${workOrderList.getOrder_no()}</td>
							<td>${workOrderList.getWo_no()}</td>
							<td>${workOrderList.getPlant_cd()}</td>
							<td>${workOrderList.getLine_cd()}</td>
							<td>${workOrderList.getStart_date()}</td>
							<td>${workOrderList.getStart_shift()}</td>
							<td>${workOrderList.getEnd_date()}</td>
							<td>${workOrderList.getEnd_shift()}</td>
							<td>${workOrderList.getPlan_qty()}</td>
							<td>${workOrderList.isFlag_end()}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<br />
			<h5>생산지시서 작성</h5>
			<form action="/production/workOrder" method="post">
				<div class="form-group input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" style="width:100px"><strong>생산라인</strong></span>
					</div>
					<select name="line_cd" class="wid">
						<c:choose>
							<c:when test="<%= custOrder.getPlant_cd() == 1 %>">
								<option value="1">라인1</option>					
								<option value="2">라인2</option>
								<option value="3">라인3</option>	
							</c:when>
							<c:otherwise>
								<option value="4">라인4</option>					
								<option value="5">라인5</option>
								<option value="6">라인6</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
				<div class="form-group input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"><strong>작업시작일</strong></span>
					</div>
					<input class="wid" type="date" name="start_date" required/>
				</div>
				<div class="form-group input-group">
					<div class="input-group-prepend">
						<span class="input-group-text"><strong>시작작업조</strong></span>
					</div>
					<select name="start_shift" class="wid">
						<option value="주간">주간</option>					
						<option value="주야간">주야간</option>
						<option value="야간">야간</option>
					</select>
				</div>
				<div class="form-group input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" style="width:100px"><strong>계획수량</strong></span>
					</div>
					<input class="wid" type="text" name="plan_qty" value="<%=requiredQtys.get(0)%>" readonly/>
				</div>
				<input type="reset" value="Reset" class="btn btn-outline-success"/>
				<input type="submit" value="Submit" class="btn btn-outline-danger"/>
			</form>
		</div>
	</div>	
</body>
</html>