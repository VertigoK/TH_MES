<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<title>Home</title>
</head>
<body>
	<div class="container" align="center">
		<a href="/" class="btn btn-primary">Home</a>
		<a href="/production" class="btn btn-primary">생산현황</a>
		<a href="/quality" class="btn btn-primary">품질현황</a>
		<a href="/equipment" class="btn btn-primary">설비현황</a>
		<a href="/stock" class="btn btn-primary">재고현황</a>
		<a href="/hr" class="btn btn-primary">HR현황</a>
	</div>
	<br />
	<div class="container" align="center">
		<a href="/logInForm" class="btn btn-primary">Log In</a>
	</div>
	<br />
	<div class="container" align="center">
		<a href="/logOut" class="btn btn-primary">Log Out</a>
	</div>
	<br />
	<div class="container" align="center">
		<a href="/custOrderForm" class="btn btn-primary">주문 생성</a>
	</div>
</body>
</html>