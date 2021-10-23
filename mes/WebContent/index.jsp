<%@page import="mes.dto.ProcessBean"%>
<%@page import="mes.dto.WorkOrderBean"%>
<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.NoticeBean"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberBean member = (MemberBean) session.getAttribute("logInInfo");
	@SuppressWarnings("unchecked")
	ArrayList<NoticeBean> recentNotice = (ArrayList<NoticeBean>) session.getAttribute("recentNoticeInfo");
	@SuppressWarnings("unchecked")
	ArrayList<WorkOrderBean> workOrderTodayList = (ArrayList<WorkOrderBean>) session.getAttribute("workOrderTodayListInfo");
	@SuppressWarnings("unchecked")
	ArrayList<ProcessBean> processList = (ArrayList<ProcessBean>) session.getAttribute("processListInfo");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/lv1StyleSheet.css" />
	<title>Company</title>
	<style>
		.content {
			grid-template-columns: repeat(10, 1fr);
    		grid-template-rows: repeat(10, 1fr);
		}
		.null { width: 100%; }
		.main {
			grid-column: 1 / span 3;
			grid-row: 1 / span 5;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.notice {
			grid-column: 1 / span 3;
			grid-row: 6 / span 10;
		}
		.daily {
			grid-column: 4 / span 10;
			grid-row: 1 / span 5;
		}
		.line {
			grid-column: 4 / span 10;
			grid-row: 6 / span 10;
		}
		.inner-notice {
			display: flex;
			width: 100%;
			height: 33px;
			align-items: stretch;
			margin: 0 0 10px 0;
			overflow: hidden;
		}
		.inner-left { width: calc(100% - 60px); }
		.inner-right { 
			text-align: right;
			width: 60px;
			padding: 5px;
			background-color: #092843;
/* 			background-color: #3F5060; */
		}
		.inner-right a { color: white; }
		.flex { display: inline-flex; }
		.flex a p { display: inline-block; }
		.nullMain {
			grid-column: 1 / span 12;
			grid-row: 1 / span 12;
			display: flex;
			justify-content: center;
			align-items: center;
			overflow: hidden;
		}
		img { max-width: 100%; max-height: auto; }
	</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<c:if test="<%= member != null %>">
		<div id="navigation">
			<jsp:include page="/layout/navigation.jsp" />
		</div>
		<div class="content">
			<div class="item main">
				<img src="/layout/MES_logo.png" width="100%" alt="MES" />
			</div>
			<div class="item notice">
				<div class="inner-notice">
					<div class="inner-left"><h5>공지사항</h5></div>
					<div class="inner-right">
						<a href="/notice" class="more">더보기</a>
					</div>
				</div>
				<table>
					<tr>
						<th style="width: 30px;">No</th>
						<th>제목</th>
						<th style="width: 100px;">작성일</th>
					</tr>
					<%
					if(recentNotice != null) {
						int recentNoticeLength = recentNotice.size();
						for(int i=0; i < recentNoticeLength; i++) {
							NoticeBean notice = recentNotice.get(i);
					%>
					<tr>
						<td style="width: 30px;"><%= notice.getNotice_no() %></td>
						<td><a href="/notice/detail?notice_no=<%= notice.getNotice_no() %>"><%= notice.getTitle() %></a></td>
						<td style="width: 100px;"><%= notice.getDate() %></td>
					</tr>
					<%
						}
					}
					%>
				</table>
			</div>
			<div class="item daily">
				<h5>금일 생산일정</h5>
				<table>
					<tr>
						<th>공장코드</th>
						<th>라인코드</th>
						<th>품목코드</th>
						<th>작업시작일</th>
						<th>작업종료일</th>
						<th>계획수량</th>
						<th>작업상태</th>
					</tr>				
				<%
				if(workOrderTodayList != null) {
					int woLength = workOrderTodayList.size();
					for(int i=0; i < woLength; i++) {
						WorkOrderBean workOrderToday = workOrderTodayList.get(i);
				%>
					<tr>
						<td><%= workOrderToday.getPlant_cd() %></td>
						<td><%= workOrderToday.getLine_cd() %></td>
						<td><%= workOrderToday.getItem_cd() %></td>
						<td><%= workOrderToday.getStart_date() %></td>
						<td><%= workOrderToday.getEnd_date() %></td>
						<td><%= workOrderToday.getPlan_qty() %></td>
					<c:choose>
						<c:when test="<%=workOrderToday.isFlag_end() %>">
							<td>완료</td>
						</c:when>
						<c:otherwise>
							<td>미완료</td>
						</c:otherwise>
					</c:choose>
					</tr>
				<%		
					}
				}
				%>
				</table>
			</div>
			<div class="item line">
				<h5>비가동 라인/공정 현황</h5>
				<table>
					<tr>
						<th>공장</th>
						<th>라인</th>
						<th>공정명</th>
						<th>공정타입</th>
						<th>비고</th>
					</tr>
					<tr>
				<%
				if(processList != null) {
					int prLength = processList.size();
					for(int i=0; i < prLength; i++) {
						ProcessBean pr = processList.get(i);
				%>
						<td><%= pr.getPlant_cd() %></td>
						<td><%= pr.getLine_cd() %></td>
						<td><%= pr.getProcess_nm() %></td>
						<td><%= pr.getUse_type() %></td>
						<td><%= pr.getRemark() %></td>
				<%		
					}
				}
				%>
					</tr>					
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="<%= member == null %>">
		<div class="content null">
			<div class="item nullMain">
				<img src="/layout/MES_logo.png" height="100%" alt="MES" />
			</div>
		</div>
	</c:if>
</body>
</html>