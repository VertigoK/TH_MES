<%@page import="mes.dto.MemberBean"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mes.dto.CustomerOrderBean"%>
<%@page import="mes.dto.OurOrderBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<CustomerOrderBean> orderInList = (ArrayList<CustomerOrderBean>) request.getAttribute("orderInList");
	@SuppressWarnings("unchecked")	
	ArrayList<OurOrderBean> orderOutList = (ArrayList<OurOrderBean>) request.getAttribute("orderOutList");
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
			grid-template-rows: 38px 1fr 1fr;
		}
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
			<a href="/order/custOrderForm" class="btn btn-outline-danger"><strong>제품 주문</strong></a>
		</div>
		<div class="item">
			<h5>고객사 제품 주문 현황</h5>
			<table class="table-striped">
				<tr>
					<th>주문번호</th>
					<th>주문회사</th>
					<th>생산공장</th>
					<th>주문제품</th>
					<th>주문수량</th>
					<th>주문일</th>
					<th>납기일</th>
					<th>마감일</th>
					<th>납품여부</th>
					<th>납기지연일</th>
					<th>자재 소요량 파악 후 생산 지시</th>
				</tr>
				<c:forEach var="orderIn" items="${orderInList}">
				<tr>
					<td>${orderIn.getOrder_no()}</td>
					<td>${orderIn.getCust_cd()}</td>
					<td>${orderIn.getPlant_cd()}</td>
					<td>${orderIn.getItem_cd()}</td>
					<td>${orderIn.getOrder_qty()}</td>
					<td>${orderIn.getOrder_date()}</td>
					<td>${orderIn.getDelivery_date()}</td>
					<td>${orderIn.getFinished_date()}</td>
					<td>${orderIn.isOrder_status()}</td>
					<td>${orderIn.getDelayed_days()}</td>
					<c:choose>
						<c:when test="${orderIn.isOurorder_status() == false}">
							<td><a href="/order/inList/checkOrderStock?order_no=${orderIn.getOrder_no()}" class="btn btn-info">확인</a></td>	
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
			<h5>자재 발주 현황</h5>
			<table class="table-striped">
				<tr>
					<th>주문번호</th>
					<th>공급회사</th>
					<th>납품공장</th>
					<th>납품자재</th>
					<th>주문수량</th>
					<th>주문일</th>
					<th>납품상태</th>
				</tr>
				<c:forEach var="orderOut" items="${orderOutList}">
				<tr>
					<td>${orderOut.getOrder_no()}</td>
					<td>${orderOut.getCust_cd()}</td>
					<td>${orderOut.getPlant_cd()}</td>
					<td>${orderOut.getItem_cd()}</td>
					<td>${orderOut.getOrder_qty()}</td>
					<td>${orderOut.getOrder_dt()}</td>
					<td>${orderOut.isOrder_status()}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>