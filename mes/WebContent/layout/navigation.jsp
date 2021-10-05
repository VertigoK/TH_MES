<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <script src= "/js/navCssControll.js"></script>
    <style>
    	html, body {
    		margin: 0;
    		height: 100%;
    		width: 100%;
    	}
    	#tab {
    		margin: 1px 0;
    		float: left;
    		width: 50px;
    		height: 100%;
    		background-color: #0D0D0D;
    	}
    	#tab a {
			display: block;
			background: transparent;
			color: white;
			margin: 0 auto;
			padding: 10px 16px;
			border: none;
			outline: none;
			text-align: left;
			text-decoration: none;
			cursor: pointer;
			transition: 0.3s;
			font-size: 17px;
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
		i {
			text-align: center;
			width: 100%;
		}
    </style>
</head>
<body>
	<div id="tab">
		<a href="/" class="btn shadow-none" id="home"><i class="fas fa-home"></i></a>
		<a href="/production" class="btn shadow-none" id="product"><i class="fas fa-cogs"></i></a> <!-- 생산 -->
		<a href="/quality" class="btn shadow-none" id="quality"><i class="fas fa-chart-line"></i></a> <!-- 품질 -->
		<a href="/equipment" class="btn shadow-none" id="equipment"><i class="fas fa-sliders-h"></i></a> <!-- 설비 -->
		<a href="/stock" class="btn shadow-none" id="stock"><i class="fas fa-warehouse"></i></a> <!-- 재고 -->
		<a href="/hr" class="btn shadow-none" id="hr"><i class="fas fa-id-badge"></i></a> <!-- HR -->
		<a href="javascript:window.history.back();" class="btn shadow-none" id="hidden"><i class="fas fa-arrow-circle-left"></i></a> <!-- 뒤로가기 -->
	</div>
</body>
</html>