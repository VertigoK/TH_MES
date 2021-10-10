<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>글쓰기 성공</title>
	<style>
		.item {
			display: grid;
			grid-template-columns: repeat(5, 1fr);
			grid-template-rows: repeat(5, 1fr);
		}
		h3 {
			grid-column: 2 / span 3;
			grid-row: 1 / span 3;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.list {
			grid-column: 3;
			grid-row: 3;
		}
		.list .btn {
			width: 100%;
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
			<h3>글쓰기 성공!</h3>
			<div class="list">
				<a href="/notice" class="btn shadow-none" >목록으로</a>
			</div>
		</div>
	</div>
</body>
</html>