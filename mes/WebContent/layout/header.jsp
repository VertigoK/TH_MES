<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		html, body {
			margin: 0;
		}
		.container-fluid {
			display: table;
			padding: 0 5px;
			width: 100%;
			height: 20px;
			background-color: #C0CDD6;
		}
		.left p {
			display: table-cell;
			line-height: 40px;
			letter-spacing: 5px;
		}
		.right {
			height: 100%;
			width: 160px;
			/* width: 170px // log out */
			display: table-cell;
		}
		.right .btn {
			text-decoration: none;
			color: #0D0D0D;
			border: 1px solid #0D0D0D;
			padding: 5px 8px;
			border-radius: 5px;
			background-color: white;
		}
		.btn:hover, .btn:active {
			border: 1px solid white;
			color: white;
			background-color: gray;
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="left">
			<p><strong>Telstar-Hommel</strong></p>
		</div>
		<div class="right">
			<a href="/logInForm" class="btn shadow-none">Log In</a>
<!-- 			<a href="/logOut" class="btn shadow-none">Log out</a> -->
<!-- 			<a href="/custOrderForm" class="btn shadow-none">주문</a>		 -->
		</div>
	</div>
</body>
</html>