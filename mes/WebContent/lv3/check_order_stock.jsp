<%@page import="java.util.ArrayList"%>
<%@page import="mes.dto.CustomerOrderBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");
	@SuppressWarnings("unchecked")
	ArrayList<Integer> stockQtys = (ArrayList<Integer>) session.getAttribute("stockQtysInfo");
	@SuppressWarnings("unchecked")
	ArrayList<Integer> requiredQtys = (ArrayList<Integer>) session.getAttribute("requiredQtysInfo");
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
		<div class="item">
			<h5>제품 주문 정보</h5>
			<table class="table1 table-striped">
				<tr>
					<th>주문번호</th>
					<th>주문회사</th>
					<th>생산공장</th>
					<th>주문제품</th>
					<th>주문수량</th>
					<th>주문일</th>
					<th>납기일</th>
					<th>마감일</th>
					<th>납품상태</th>
					<th>납기지연일</th>
				</tr>
				<tr>
					<td><%=custOrder.getOrder_no()%></td>
					<td><%=custOrder.getItem_cd()%></td>
					<td><%=custOrder.getPlant_cd()%></td>
					<td><%=custOrder.getItem_cd()%></td>
					<td style="color:red"><%=custOrder.getOrder_qty()%></td>
					<td><%=custOrder.getOrder_date()%></td>
					<td><%=custOrder.getDelivery_date()%></td>
					<td><%=custOrder.getFinished_date()%></td>
					<td><%=custOrder.isOrder_status()%></td>
					<td><%=custOrder.getDelayed_days()%></td>
				</tr>
			</table>
			<br /><br /><br />
			<h5>[공장 <%=custOrder.getPlant_cd()%>] 제품 재고수량 및 자재 재고수량</h5>
			<table class="table1 table-striped">
				<tr>
					<th>제품</th>
					<th>제품 <%=custOrder.getItem_cd()%></th>
					<th>자재</th>
					<th>자재 1</th>
					<th>자재 2</th>
					<th>자재 3</th>
				</tr>
				<tr>
					<td>재고수량</td>
					<td><%=stockQtys.get(0)%></td>
					<td>재고수량</td>
					<td><%=stockQtys.get(1)%></td>
					<td><%=stockQtys.get(2)%></td>
					<td><%=stockQtys.get(3)%></td>
				</tr>
			</table>
			<br /><br /><br />
			<h5>[공장 <%=custOrder.getPlant_cd()%>] 생산 계획수량 및 자재 발주수량</h5>
			<table class="table1 table-striped">
				<tr>
					<th>제품</th>
					<th>제품 <%=custOrder.getItem_cd()%></th>
					<th>자재</th>
					<th>자재 1</th>
					<th>자재 2</th>
					<th>자재 3</th>
				</tr>
				<tr>
					<td style="color:red"><strong>계획수량</strong></td>
					<td style="color:red"><strong><%=requiredQtys.get(0)%></strong></td>
					<td style="color:blue"><strong>발주수량</strong></td>
					<td style="color:blue"><strong><%=requiredQtys.get(1)%></strong></td>
					<td style="color:blue"><strong><%=requiredQtys.get(2)%></strong></td>
					<td style="color:blue"><strong><%=requiredQtys.get(3)%></strong></td>
				</tr>
			</table>
			<br /><br /><br />
			<c:choose>
				<c:when test="<%=requiredQtys.get(0) == 0%>">
					<h5>제품 재고가 충분하므로 제품을 생산할 필요 없습니다. 제품을 출고하시겠습니까?</h5>
					<a href="#" class="btn btn-warning">확인</a>
				</c:when>
				<c:when test="<%=requiredQtys.get(1) == 0 && requiredQtys.get(2) == 0 && requiredQtys.get(3) == 0%>">
					<h5>자재 재고가 충분하므로 자재를 발주할 필요 없습니다. 생산지시 페이지로 이동하시겠습니까?</h5>
					<a href="/production/workOrderForm?order_no=${custOrder.getOrder_no()}" class="btn btn-warning">확인</a>
				</c:when>
				<c:otherwise>
					<h5>자재 재고가 부족하므로 발주가 필요합니다. 자재를 자동 발주하시겠습니까?</h5>
					<a href="/order/ourOrderAuto" class="btn btn-warning">확인</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>