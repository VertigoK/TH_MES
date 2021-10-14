<%@page import="mes.dto.MemberBean"%>
<%@page import="mes.dto.PageInfo"%>
<%@page import="mes.dto.NoticeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<NoticeBean> noticeList = (ArrayList<NoticeBean>) request.getAttribute("noticeList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	MemberBean member = (MemberBean) session.getAttribute("logInInfo");
	
	int totalPage = pageInfo.getTotalPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int numOfPages = pageInfo.getNumOfPages();
	int limit = pageInfo.getLimit();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<link rel="stylesheet" href="/css/lv1StyleSheet.css" />
	<title>Company</title>
	<style>
		.content {
			grid-template-rows: 33px 1fr;
		}
		table { margin-bottom: 10px; }
		.title { overflow: hidden; }
		.top { display: table; width: 100%; padding: 10px; }
		.top-left { display: table-cell; }
		.top-right { display: table-cell; text-align: right; }
		.top-right .btn { border:1px solid; border-radius: 5px; padding: 0 8px; }
	</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
		<jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content">
		<div class="item title">
			<h5>공지사항</h5>
		</div>
		<div class="item">
			<div class="top">
				<div class="top-left"><p>총 <%=pageInfo.getListCount() %>건</p></div>
					<c:if test='<%= member != null && member.getMem_id().equals("admin") %>'>
						<div class="top-right"><a href="/notice/writeForm" class="btn shadow-none">글쓰기</a></div>
					</c:if>
			</div>
			<table>
				<tr>
					<th style="width: 30px;">No</th>
					<th>제목</th>
					<th style="width: 200px;">작성일</th>
				</tr>
				<c:forEach var="notice" items="${ noticeList }">
					<tr>
						<td style="width: 30px;">${ notice.getNotice_no() }</td>
						<td><a href="/notice/detail?notice_no=${ notice.getNotice_no() }">${ notice.getTitle() }</a></td>
						<td style="width: 200px;">${ notice.getDate() }</td>
					</tr>
				</c:forEach>
			</table>
			<div class="container">
				<ul class="pagination justify-content-center">
					<c:if test="<%= startPage != 1 %>">
						<li class="page-item"><a href="/notice?page=1&limit=<%= limit %>" class="page-link"><i class="fas fa-angle-double-left"></i></a></li>
						<li class="page-item"><a href="/notice?page=<%= startPage-numOfPages %>&limit=<%= limit %>" class="page-link"><i class="fas fa-angle-left"></i></a></li>
					</c:if>

					<c:forEach var="page_num" begin="<%= startPage %>" end="<%= endPage %>">
						<li class="page-item"><a href="/notice?page=${page_num}&limit=<%= limit %>" class="page-link">${page_num}</a></li>
					</c:forEach>
					
					<c:if test="<%= endPage < totalPage %>">
						<li class="page-item"><a href="/notice?page=<%= endPage+1 %>&limit=<%= limit %>" class="page-link"><i class="fas fa-angle-right"></i></a></li>
						<li class="page-item"><a href="/notice?page=<%= totalPage %>&limit=<%= limit %>" class="page-link"><i class="fas fa-angle-double-right"></i></a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>