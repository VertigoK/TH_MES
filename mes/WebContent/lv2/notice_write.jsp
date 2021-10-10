<%@page import="mes.dto.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<link rel="stylesheet" href="/css/lv1StyleSheet.css" />
	<title>공지사항 글쓰기</title>
	<style>
		.item {
			display: grid;
			justify-content: center;
			align-content: center;
			grid-template-columns: repeat(11, 1fr);
			grid-template-rows: repeat(11, 1fr);
		}
		h5 {
			background-color: white;
			color: black;
		}
		.write {
			grid-column: 2 / span 9;
			grid-row: 2 / span 9;
		}
		form { height: 100%; }
		table { height: 100%; }
		th, td { border-bottom: 1px solid black; border-top: 1px solid black; background-color: white; }
		input { width: 100%; }
		textarea { width: 100%; height: 90%; }
		td .btn { width: 100px; border: 3px solid; border-radius: 30px; background-color: white; }
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
			<c:choose>
				<c:when test='<%= member != null && member.getMem_id().equals("admin") %>'>
					<div class="write">
						<h5>글쓰기</h5>
						<form action="/notice/write" method="post">
							<table>
								<tr>
									<th>제목</th>
									<td><input type="text" name="title" id="title" /></td>
								</tr>
								<tr>
									<th>내용</th>
									<td><textarea name="content" id="content" cols="30" rows="10"></textarea></td>
								</tr>
								<tr>
									<td colspan="2" style="border-bottom: none;">
										<input type="submit" class="btn" value="등록" /> &nbsp; &nbsp; &nbsp; &nbsp;
										<input type="button" class="btn" value="취소" onclick="history.back()" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="write">
						<h3>잘못된 접근입니다!</h3>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</body>
</html>