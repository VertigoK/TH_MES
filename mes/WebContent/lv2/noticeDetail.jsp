<%@page import="mes.dto.NoticeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	NoticeBean notice = (NoticeBean) request.getAttribute("notice");
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
	<title>${ notice.getTitle() }</title>
	<style>
		.item {
			display: grid;
			justify-content: center;
			align-content: center;
			grid-template-columns: repeat(11, 1fr);
			grid-template-rows: repeat(11, 1fr);
		}
		.notice-detail {
			grid-column: 2 / span 9;
			grid-row: 2 / span 9;
			border-bottom: 2px solid;
		}
		h5 {
			background-color: white;
			color: black;
		}
		.notice-title {
			display: table;
			width: 100%;
			padding: 10px;
			border-top: 2px solid;
			border-bottom: 2px solid;
			text-align: center;
		}
		.title-left { display: table-cell; text-align:left; font-size: 19px; }
		.title-right { display: table-cell; text-align: right; }
		.notice-content { padding: 10px; font-size: 19px; }
		.back {
			grid-column: 6;
			grid-row: 11;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.back .btn {
			width: 200px;
			border: 3px solid;
			border-radius: 30px;
		}
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
		<div class="item">
			<div class="notice-detail">
				<h5>공지사항</h5>
				<div class="notice-title">
					<span class="title-left">${ notice.getTitle() }</span>
					<span class="title-right">작성일 ${ notice.getDate() }</span>
				</div>
				<div class="notice-content" style="white-space:pre-line;">
					${ notice.getContent() }
				</div>
			</div>
			<div class="back">
				<a href="/notice" class="btn shadow-none">목록으로</a>
			</div>
		</div>
	</div>
</body>
</html>