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
<title>Insert title here</title>
</head>

	<style>
		button.active {
			background-color: green;
		}
	</style>

<body>
	
	<div id="wrap">
		<button class="btn active">1</button>
		<button class="btn">2</button>
		<button class="btn">3</button>
		<button class="btn">4</button>
	</div>
	
	<script>
		$(function() {
			var btns = $(".btn");
			btns.click(function() {
				btns.removeClass("active");
				$(this).addClass("active");
			})
		})
	</script>
	
</body>
</html>