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
		.container-fluid {
			display: table;
			padding: 0 5px;
			height: 40px;
		}
		.left p {
			display: table-cell;
			vertical-align: middle;
			letter-spacing: 5px;
			line-height: 40px;
		}
		.right {
			width: 100px;
			display: table-cell;
			vertical-align: middle;
		}
		.right .btn {
			display: block;
			vertical-align: middle;
			border: 1px solid #0D0D0D;
		}
		.btn:hover, .btn:active {
			border: 1px solid white;
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
			<a href="#" class="btn shadow-none">로그인</a>
		</div>
	</div>
</body>
</html>