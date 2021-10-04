<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src= "/js/navCssControll.js"></script>
    <style>
    	html, body {
    		margin: 0;
    		height: 100%;
    		width: 100%;
    	}
    	#tab {
    		float: left;
    		width: 100px;
    		height: 100%;
    		background-color: #0D0D0D;
    	}
    	#tab a {
			display: block;
			background: transparent;
			color: white;
			width: 100%;
			padding: 10px 16px;
			border: none;
			outline: none;
			text-align: left;
			text-decoration: none;
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
		<a href="/" class="btn shadow-none" id="home">Home</a>
		<a href="/production" class="btn shadow-none" id="product">생산현황</a>
		<a href="/quality" class="btn shadow-none" id="quality">품질현황</a>
		<a href="/equipment" class="btn shadow-none" id="equipment">설비현황</a>
		<a href="/stock" class="btn shadow-none" id="stock">재고현황</a>
		<a href="/order" class="btn shadow-none" id="hr">주문현황</a>
		<a href="/hr" class="btn shadow-none" id="hr">HR현황</a>
	</div>
</body>
</html>