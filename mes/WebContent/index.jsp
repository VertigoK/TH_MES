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
	<title>Telstar-Hommel</title>
	<style>
		.content {
			grid-template-columns: repeat(10, 1fr);
    		grid-template-rows: repeat(7, 1fr);
		}
		.main {
			grid-column: 1 / span 7;
			grid-row: 1 / span 4;
		}
		.daily {
			grid-column: 8 / span 10;
			grid-row: 1 / span 4;
		}
		.line {
			grid-column: 6 / span 12;
			grid-row: 5 / span 7;
		}
		.notice {
			grid-column: 1 / span 5;
			grid-row: 5 / span 7;
		}
		.inner-notice { display: table; }
		.inner-left { display: table-cell; }
		.inner-right { display: table-cell; }
		.flex { display: inline-flex; }
		.flex a p { display: inline-block; }
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
		<div class="item main">
			<img src="/layout/telstar-logo.png" alt="telstar-logo" />
			<h4 class="teslar">Telstar</h4>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
			Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
			when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
		</div>
		<div class="item daily">
			<h5>금일 생산일정</h5>
			<table>
				<tr>
					<th>라인</th>
					<th>시작시간</th>
					<th>종료시간</th>
					<th>qty</th>
					<th>내용</th>
				</tr>
				<c:forEach var="test" begin="1" end="8">
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="item notice">
			<div class="inner-notice">
				<div class="inner-left"><h5>공지사항</h5></div>
				<div class="inner-right">
					<div class="flex">
						<a href="/notice"><p>더보기</p><i class="fas fa-caret-right"></i></a>
					</div>
				</div>
			</div>
			<table>
				<tr>
					<th style="width: 30px;">No</th>
					<th>제목</th>
					<th style="width: 60px;">작성일</th>
				</tr>
				<c:forEach var="test" begin="1" end="6">
					<tr>
						<td style="width: 30px;">1</td>
						<td>2</td>
						<td style="width: 60px;">3</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="item line">
			<h5>라인별 가동현황</h5>
			<table class="line-table" border="1">
			<c:forEach var="line" begin="1" end="6">
				<tr>
					<th>라인${ line }</th>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>