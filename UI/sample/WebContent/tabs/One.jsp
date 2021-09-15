<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Home</title>
<style>
	html, body {
		margin: 0;
		width: 100%;
		height: 100%;
	}
	#notice {
		background-color: blue;
	}
	#main {
		background-color: pink;
	}
	.container {
		background-color: yellow;
	}
</style>
<script>
	$('#main').load('/tabs/Two.jsp');
	$('#notice').load('/tabs/Three.jsp');
	$(".container").css("height", $(window).height() - $("h1").height());
</script>
</head>
<body>
	<h1>home</h1>
	<div class="container">
		<div id="notice">notice</div>
		<div id="main">main</div>	
	</div>
</body>
</html>