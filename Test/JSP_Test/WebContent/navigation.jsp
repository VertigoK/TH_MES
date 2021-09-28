<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src= "js/ajax.js"></script>
    <title>Home</title>
    <style>
		html, body {
			margin: 0;
			height: 100%;
			width: 100%;
		}
		#tab {
			float: left;
			width: 110px;
			height: 100%;
			background-color: #0D0D0D;
		}
		a { outline: none; }
		#tab a {
			display: block;
			background: transparent;
			color: white;
			width: 100%;
			padding: 10px 16px;
			border: none;
			outline: none;
			text-align: left;
			cursor: pointer;
			transition: 0.3s;
			font-size: 17px;
		}
		a:focus, a:active, a:visited {
		    outline: none;
		    border: none;
		}
		#tab a:hover, #tab a.active {
			background-color: white;
			border-radius: 0;
			border-left-style: solid;
			border-left-color: #0D0D0D;
			color: black;
			font-weight: bold;
			overflow: hidden;
		}
    </style>
</head>
<body>
	<div id="tab">
		<a href="/" class="btn" id="home">Home</a>
		<a href="page1.jsp" class="btn" id="product">생산현황</a>
		<a href="page2.jsp" class="btn" id="quality">품질현황</a>
		<a href="page3.jsp" class="btn" id="equipment">설비현황</a>
		<a href="page4.jsp" class="btn" id="stock">재고현황</a>
		<a href="page5.jsp" class="btn" id="hr">HR현황</a>
	</div>
</body>
</html>