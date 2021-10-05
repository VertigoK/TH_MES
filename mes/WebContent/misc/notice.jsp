<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/lv1StyleSheet.css" />
	<title>공지사항</title>
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
			<h5>공지사항</h5>
			<table>
				<tr>
					<th style="width: 30px;">No</th>
					<th>제목</th>
					<th style="width: 60px;">작성일</th>
				</tr>
				<c:forEach var="test" begin="1" end="20">
					<tr>
						<td style="width: 30px;">1</td>
						<td>2</td>
						<td style="width: 60px;">3</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>